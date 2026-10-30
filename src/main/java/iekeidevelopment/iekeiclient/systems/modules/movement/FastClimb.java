/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.systems.modules.movement;

import iekeidevelopment.iekeiclient.events.world.TickEvent;
import iekeidevelopment.iekeiclient.mixin.LivingEntityAccessor;
import iekeidevelopment.iekeiclient.settings.BoolSetting;
import iekeidevelopment.iekeiclient.settings.DoubleSetting;
import iekeidevelopment.iekeiclient.settings.Setting;
import iekeidevelopment.iekeiclient.settings.SettingGroup;
import iekeidevelopment.iekeiclient.systems.modules.Categories;
import iekeidevelopment.iekeiclient.systems.modules.Module;
import iekeidevelopment.iekeiclient.systems.modules.Modules;
import iekeidevelopment.iekeiclient.systems.modules.world.Timer;
import iekeidevelopment.orbit.EventHandler;
import net.minecraft.block.Blocks;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.util.math.Vec3d;

public class FastClimb extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> timerMode = sgGeneral.add(new BoolSetting.Builder()
        .name("timer-mode")
        .description("Use timer.")
        .defaultValue(false)
        .build()
    );

    private final Setting<Double> speed = sgGeneral.add(new DoubleSetting.Builder()
        .name("climb-speed")
        .description("Your climb speed.")
        .defaultValue(0.2872)
        .min(0.0)
        .visible(() -> !timerMode.get())
        .build()
    );

    private final Setting<Double> timer = sgGeneral.add(new DoubleSetting.Builder()
        .name("timer")
        .description("The timer value for Timer.")
        .defaultValue(1.436)
        .min(1)
        .sliderMin(1)
        .visible(timerMode::get)
        .build()
    );

    private boolean resetTimer;

    public FastClimb() {
        super(Categories.Movement, "fast-climb", "Allows you to climb faster.");
    }

    @Override
    public void onActivate() {
        resetTimer = false;
    }

    @EventHandler
    private void onPreTick(TickEvent.Pre event) {
        if (timerMode.get()) {
            if (climbing()) {
                resetTimer = false;
                Modules.get().get(Timer.class).setOverride(timer.get());
            } else if (!resetTimer) {
                Modules.get().get(Timer.class).setOverride(Timer.OFF);
                resetTimer = true;
            }
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (!timerMode.get() && climbing()) {
            Vec3d velocity = mc.player.getVelocity();
            mc.player.setVelocity(velocity.x, speed.get(), velocity.z);
        }
    }

    private boolean climbing() {
        return (mc.player.horizontalCollision || ((LivingEntityAccessor) mc.player).meteor$isJumping()) && (mc.player.isClimbing() || mc.player.getBlockStateAtPos().isOf(Blocks.POWDER_SNOW) && PowderSnowBlock.canWalkOnPowderSnow(mc.player));
    }
}
