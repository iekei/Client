package com.iekeiclient.addons.rejects.modules;

import com.iekeiclient.addons.rejects.IekeiRejectsAddon;
import com.iekeiclient.addons.rejects.events.OffGroundSpeedEvent;
import com.iekeiclient.engine.events.world.TickEvent;
import com.iekeiclient.engine.mixininterface.IVec3d;
import com.iekeiclient.engine.settings.DoubleSetting;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;

public class Jetpack extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Double> jetpackSpeed = sgGeneral.add(new DoubleSetting.Builder()
            .name("jetpack-speed")
            .description("How fast while ascending.")
            .defaultValue(0.42)
            .min(0)
            .sliderMax(1)
            .build()
    );

    public Jetpack() {
        super(IekeiRejectsAddon.CATEGORY, "jetpack", "Flies as if using a jetpack.");
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (mc.options.jumpKey.isPressed()) {
            ((IVec3d) mc.player.getVelocity()).iekei$setY(jetpackSpeed.get());
        }
    }

    @EventHandler
    private void onOffGroundSpeed(OffGroundSpeedEvent event) {
        event.speed = mc.player.getMovementSpeed() * jetpackSpeed.get().floatValue();
    }
}

