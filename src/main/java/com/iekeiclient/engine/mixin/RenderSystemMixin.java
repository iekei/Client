/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.iekeiclient.engine.renderer.MeshUniforms;
import com.iekeiclient.engine.utils.render.postprocess.ChamsShader;
import com.iekeiclient.engine.utils.render.postprocess.OutlineUniforms;
import com.iekeiclient.engine.utils.render.postprocess.PostProcessShader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public abstract class RenderSystemMixin {
    @Inject(method = "flipFrame", at = @At("TAIL"))
    private static void iekei$flipFrame(CallbackInfo info) {
        MeshUniforms.flipFrame();
        PostProcessShader.flipFrame();
        ChamsShader.flipFrame();
        OutlineUniforms.flipFrame();
    }
}
