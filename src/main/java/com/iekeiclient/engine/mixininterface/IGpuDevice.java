/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixininterface;

import com.mojang.blaze3d.systems.RenderPass;

public interface IGpuDevice {
    /**
     * Currently there can only be a single scissor pushed at once.
     */
    void iekei$pushScissor(int x, int y, int width, int height);

    void iekei$popScissor();

    /**
     * This is an *INTERNAL* method, it shouldn't be called.
     */
    @Deprecated
    @SuppressWarnings("DeprecatedIsStillUsed")
    void iekei$onCreateRenderPass(RenderPass pass);
}
