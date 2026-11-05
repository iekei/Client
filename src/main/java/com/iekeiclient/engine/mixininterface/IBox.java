/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixininterface;

import net.minecraft.util.math.BlockPos;

public interface IBox {
    void iekei$expand(double v);

    void iekei$set(double x1, double y1, double z1, double x2, double y2, double z2);

    default void iekei$set(BlockPos pos) {
        iekei$set(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
    }
}
