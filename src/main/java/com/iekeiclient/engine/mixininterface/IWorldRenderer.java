/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixininterface;

import net.minecraft.client.gl.Framebuffer;

public interface IWorldRenderer {
    void iekei$pushEntityOutlineFramebuffer(Framebuffer framebuffer);

    void iekei$popEntityOutlineFramebuffer();
}
