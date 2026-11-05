/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.systems.modules.world;

import com.iekeiclient.engine.events.packets.PacketEvent;
import com.iekeiclient.engine.mixininterface.IPlayerInteractEntityC2SPacket;
import com.iekeiclient.engine.systems.modules.Categories;
import com.iekeiclient.engine.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;

public class MountBypass extends Module {
    private boolean dontCancel;

    public MountBypass() {
        super(Categories.World, "mount-bypass", "Allows you to bypass the IllegalStacks plugin and put chests on entities.");
    }

    @EventHandler
    public void onSendPacket(PacketEvent.Send event) {
        if (dontCancel) {
            dontCancel = false;
            return;
        }

        if (event.packet instanceof IPlayerInteractEntityC2SPacket packet) {
            if (packet.iekei$getType() == PlayerInteractEntityC2SPacket.InteractType.INTERACT_AT && packet.iekei$getEntity() instanceof AbstractDonkeyEntity) event.cancel();
        }
    }
}
