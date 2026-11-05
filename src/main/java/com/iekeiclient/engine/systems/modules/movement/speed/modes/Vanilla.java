/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.systems.modules.movement.speed.modes;

import com.iekeiclient.engine.events.entity.player.PlayerMoveEvent;
import com.iekeiclient.engine.mixininterface.IVec3d;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.engine.systems.modules.movement.Anchor;
import com.iekeiclient.engine.systems.modules.movement.speed.SpeedMode;
import com.iekeiclient.engine.systems.modules.movement.speed.SpeedModes;
import com.iekeiclient.engine.utils.player.PlayerUtils;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.Vec3d;

public class Vanilla extends SpeedMode {
    public Vanilla() {
        super(SpeedModes.Vanilla);
    }

    @Override
    public void onMove(PlayerMoveEvent event) {
        Vec3d vel = PlayerUtils.getHorizontalVelocity(settings.vanillaSpeed.get());
        double velX = vel.getX();
        double velZ = vel.getZ();

        if (mc.player.hasStatusEffect(StatusEffects.SPEED)) {
            double value = (mc.player.getStatusEffect(StatusEffects.SPEED).getAmplifier() + 1) * 0.205;
            velX += velX * value;
            velZ += velZ * value;
        }

        Anchor anchor = Modules.get().get(Anchor.class);
        if (anchor.isActive() && anchor.controlMovement) {
            velX = anchor.deltaX;
            velZ = anchor.deltaZ;
        }

        ((IVec3d) event.movement).iekei$set(velX, event.movement.y, velZ);
    }
}
