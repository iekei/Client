package com.iekeiclient.engine.utils.render.postprocess;

import com.mojang.blaze3d.systems.RenderSystem;
import com.iekeiclient.engine.mixininterface.IWorldRenderer;

import java.util.OptionalInt;

import static com.iekeiclient.engine.IekeiClient.mc;

public abstract class EntityShader extends PostProcessShader {
    @Override
    public boolean beginRender() {
        if (super.beginRender()) {
            RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Iekei EntityShader", framebuffer.getColorAttachmentView(), OptionalInt.of(0)).close();
            return true;
        }

        return false;
    }

    @Override
    protected void preDraw() {
        ((IWorldRenderer) mc.worldRenderer).iekei$pushEntityOutlineFramebuffer(framebuffer);
    }

    @Override
    protected void postDraw() {
        ((IWorldRenderer) mc.worldRenderer).iekei$popEntityOutlineFramebuffer();
    }

    public void endRender() {
        endRender(() -> vertexConsumerProvider.draw());
    }
}
