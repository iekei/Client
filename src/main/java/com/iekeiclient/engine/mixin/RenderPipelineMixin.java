/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.iekeiclient.engine.mixininterface.IRenderPipeline;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(RenderPipeline.class)
public abstract class RenderPipelineMixin implements IRenderPipeline {
    @Unique
    private boolean lineSmooth;

    @Override
    public void iekei$setLineSmooth(boolean lineSmooth) {
        this.lineSmooth = lineSmooth;
    }

    @Override
    public boolean iekei$getLineSmooth() {
        return lineSmooth;
    }
}
