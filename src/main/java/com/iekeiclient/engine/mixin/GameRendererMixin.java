/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import com.iekeiclient.engine.IekeiClient;
import com.iekeiclient.engine.events.render.GetFovEvent;
import com.iekeiclient.engine.events.render.Render3DEvent;
import com.iekeiclient.engine.events.render.RenderAfterWorldEvent;
import com.iekeiclient.engine.gui.WidgetScreen;
import com.iekeiclient.engine.mixininterface.IVec3d;
import com.iekeiclient.engine.renderer.IekeiRenderPipelines;
import com.iekeiclient.engine.renderer.Renderer3D;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.engine.systems.modules.render.Freecam;
import com.iekeiclient.engine.systems.modules.render.NoRender;
import com.iekeiclient.engine.systems.modules.render.Zoom;
import com.iekeiclient.engine.systems.modules.world.HighwayBuilder;
import com.iekeiclient.engine.utils.Utils;
import com.iekeiclient.engine.utils.render.CustomBannerGuiElementRenderer;
import com.iekeiclient.engine.utils.render.NametagUtils;
import com.iekeiclient.engine.utils.render.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gui.render.SpecialGuiElementRenderer;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.profiler.Profilers;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    public abstract void updateCrosshairTarget(float tickDelta);

    @Shadow
    public abstract void reset();

    @Shadow
    @Final
    private Camera camera;

    @Unique
    private Renderer3D renderer;

    @Unique
    private Renderer3D depthRenderer;

    @Unique
    private final MatrixStack matrices = new MatrixStack();

    @Shadow
    protected abstract void bobView(MatrixStack matrices, float tickDelta);

    @Shadow
    protected abstract void tiltViewWhenHurt(MatrixStack matrices, float tickDelta);

    @Shadow
    @Final
    private BufferBuilderStorage buffers;

    @Shadow
    @Final
    private GuiRenderer guiRenderer;

    @Shadow
    @Final
    private FogRenderer fogRenderer;

    @Shadow
    @Final
    GuiRenderState guiState;

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/render/GuiRenderer;<init>(Lnet/minecraft/client/gui/render/state/GuiRenderState;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/render/command/RenderDispatcher;Ljava/util/List;)V"))
    private List<SpecialGuiElementRenderer<?>> iekei$addSpecialRenderers(List<SpecialGuiElementRenderer<?>> list) {
        list = new ArrayList<>(list);
        list.add(new CustomBannerGuiElementRenderer(buffers.getEntityVertexConsumers(), client.getAtlasManager()));

        return List.of(list.toArray(new SpecialGuiElementRenderer<?>[0]));
    }

    @Inject(method = "renderWorld", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = {"ldc=hand"}))
    private void onRenderWorld(RenderTickCounter tickCounter, CallbackInfo ci, @Local(ordinal = 0) Matrix4f projection, @Local(ordinal = 1) Matrix4f position, @Local(ordinal = 1) float tickDelta, @Local MatrixStack matrixStack) {
        if (!Utils.canUpdate()) return;

        Profilers.get().push(IekeiClient.MOD_ID + "_render");

        // Create renderer and event

        if (renderer == null) renderer = new Renderer3D(IekeiRenderPipelines.WORLD_COLORED_LINES, IekeiRenderPipelines.WORLD_COLORED);
        if (depthRenderer == null) depthRenderer = new Renderer3D(IekeiRenderPipelines.WORLD_COLORED_LINES_DEPTH, IekeiRenderPipelines.WORLD_COLORED_DEPTH);
        Render3DEvent event = Render3DEvent.get(matrixStack, renderer, depthRenderer, tickDelta, camera.getCameraPos().x, camera.getCameraPos().y, camera.getCameraPos().z);

        // Call utility classes

        RenderUtils.updateScreenCenter(projection, position);
        NametagUtils.onRender(position);

        // Update model view matrix

        RenderSystem.getModelViewStack().pushMatrix().mul(position);

        matrices.push();
        tiltViewWhenHurt(matrices, camera.getLastTickProgress());
        if (client.options.getBobView().getValue()) bobView(matrices, camera.getLastTickProgress());
        RenderSystem.getModelViewStack().mul(matrices.peek().getPositionMatrix().invert());
        matrices.pop();

        // Render

        renderer.begin();
        depthRenderer.begin();
        IekeiClient.EVENT_BUS.post(event);
        renderer.render(matrixStack);
        depthRenderer.render(matrixStack);

        // Revert model view matrix

        RenderSystem.getModelViewStack().popMatrix();

        Profilers.get().pop();
    }

    @Inject(method = "renderWorld", at = @At("TAIL"))
    private void onRenderWorldTail(CallbackInfo info) {
        IekeiClient.EVENT_BUS.post(RenderAfterWorldEvent.get());
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/render/GuiRenderer;render(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V", shift = At.Shift.AFTER))
    private void onRenderGui(RenderTickCounter tickCounter, boolean tick, CallbackInfo info) {
        if (client.currentScreen instanceof WidgetScreen widgetScreen) {
            guiState.clear();
            var mouseX = (int) client.mouse.getScaledX(client.getWindow());
            var mouseY = (int) client.mouse.getScaledY(client.getWindow());

            var context = new DrawContext(client, guiState, mouseX, mouseY);

            widgetScreen.renderCustom(context, mouseX, mouseY, tickCounter.getDynamicDeltaTicks());

            RenderSystem.getDevice().createCommandEncoder().clearDepthTexture(client.getFramebuffer().getDepthAttachment(), 1.0);
            guiRenderer.render(fogRenderer.getFogBuffer(FogRenderer.FogType.NONE));
            guiRenderer.incrementFrame();
        }
    }

    @Inject(method = "showFloatingItem", at = @At("HEAD"), cancellable = true)
    private void onShowFloatingItem(ItemStack floatingItem, CallbackInfo info) {
        if (floatingItem.getItem() == Items.TOTEM_OF_UNDYING && Modules.get().get(NoRender.class).noTotemAnimation()) {
            info.cancel();
        }
    }

    @ModifyExpressionValue(method = "renderWorld", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", ordinal = 0))
    private float applyCameraTransformationsMathHelperLerpProxy(float original) {
        return Modules.get().get(NoRender.class).noNausea() ? 0 : original;
    }

    @ModifyReturnValue(method = "getFov", at = @At("RETURN"))
    private float modifyFov(float original) {
        return IekeiClient.EVENT_BUS.post(GetFovEvent.get(original)).fov;
    }

    // Freecam

    @Unique
    private boolean freecamSet = false;

    @Inject(method = "updateCrosshairTarget", at = @At("HEAD"), cancellable = true)
    private void updateTargetedEntityInvoke(float tickDelta, CallbackInfo info) {
        Freecam freecam = Modules.get().get(Freecam.class);
        boolean highwayBuilder = Modules.get().isActive(HighwayBuilder.class);

        if ((freecam.isActive() || highwayBuilder) && client.getCameraEntity() != null && !freecamSet) {
            info.cancel();
            Entity cameraE = client.getCameraEntity();

            double x = cameraE.getX();
            double y = cameraE.getY();
            double z = cameraE.getZ();
            double lastX = cameraE.lastX;
            double lastY = cameraE.lastY;
            double lastZ = cameraE.lastZ;
            float yaw = cameraE.getYaw();
            float pitch = cameraE.getPitch();
            float lastYaw = cameraE.lastYaw;
            float lastPitch = cameraE.lastPitch;

            if (highwayBuilder) {
                cameraE.setYaw(camera.getYaw());
                cameraE.setPitch(camera.getPitch());
            } else {
                ((IVec3d) cameraE.getEntityPos()).iekei$set(freecam.pos.x, freecam.pos.y - cameraE.getEyeHeight(cameraE.getPose()), freecam.pos.z);
                cameraE.lastX = freecam.prevPos.x;
                cameraE.lastY = freecam.prevPos.y - cameraE.getEyeHeight(cameraE.getPose());
                cameraE.lastZ = freecam.prevPos.z;
                cameraE.setYaw(freecam.yaw);
                cameraE.setPitch(freecam.pitch);
                cameraE.lastYaw = freecam.lastYaw;
                cameraE.lastPitch = freecam.lastPitch;
            }

            freecamSet = true;
            updateCrosshairTarget(tickDelta);
            freecamSet = false;

            ((IVec3d) cameraE.getEntityPos()).iekei$set(x, y, z);
            cameraE.lastX = lastX;
            cameraE.lastY = lastY;
            cameraE.lastZ = lastZ;
            cameraE.setYaw(yaw);
            cameraE.setPitch(pitch);
            cameraE.lastYaw = lastYaw;
            cameraE.lastPitch = lastPitch;
        }
    }

    @Inject(method = "renderHand", at = @At("HEAD"), cancellable = true)
    private void renderHand(float tickProgress, boolean sleeping, Matrix4f positionMatrix, CallbackInfo ci) {
        if (!Modules.get().get(Freecam.class).renderHands() ||
            !Modules.get().get(Zoom.class).renderHands())
            ci.cancel();
    }
}
