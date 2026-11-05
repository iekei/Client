/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.systems.modules.player;

import com.iekeiclient.engine.events.game.OpenScreenEvent;
import com.iekeiclient.engine.systems.modules.Categories;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.engine.systems.modules.render.WaypointsModule;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.EventPriority;
import net.minecraft.client.gui.screen.DeathScreen;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super(Categories.Player, "auto-respawn", "Automatically respawns after death.");
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onOpenScreenEvent(OpenScreenEvent event) {
        if (!(event.screen instanceof DeathScreen)) return;

        Modules.get().get(WaypointsModule.class).addDeath(mc.player.getEntityPos());
        mc.player.requestRespawn();
        event.cancel();
    }
}
