package com.iekeiclient.addons.rejects.modules;

import com.iekeiclient.addons.rejects.IekeiRejectsAddon;
import com.iekeiclient.engine.events.world.TickEvent;
import com.iekeiclient.engine.mixin.LivingEntityAccessor;
import meteordevelopment.orbit.EventHandler;
import com.iekeiclient.engine.systems.modules.Module;

public class NoJumpDelay extends Module {

    public NoJumpDelay() {
        super(IekeiRejectsAddon.CATEGORY, "no-jump-delay", "NoJumpDelay.");
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        ((LivingEntityAccessor) mc.player).iekei$setJumpCooldown(0);
    }
}
