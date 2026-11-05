/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixininterface;

import net.minecraft.util.math.Vec3i;
import org.joml.Vector3d;

public interface IVec3d {
    void iekei$set(double x, double y, double z);

    default void iekei$set(Vec3i vec) {
        iekei$set(vec.getX(), vec.getY(), vec.getZ());
    }

    default void iekei$set(Vector3d vec) {
        iekei$set(vec.x, vec.y, vec.z);
    }

    void iekei$setXZ(double x, double z);

    void iekei$setY(double y);
}
