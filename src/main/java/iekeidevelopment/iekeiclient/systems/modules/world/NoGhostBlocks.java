/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.systems.modules.world;

import iekeidevelopment.iekeiclient.events.entity.player.BreakBlockEvent;
import iekeidevelopment.iekeiclient.events.entity.player.PlaceBlockEvent;
import iekeidevelopment.iekeiclient.settings.BoolSetting;
import iekeidevelopment.iekeiclient.settings.Setting;
import iekeidevelopment.iekeiclient.settings.SettingGroup;
import iekeidevelopment.iekeiclient.systems.modules.Categories;
import iekeidevelopment.iekeiclient.systems.modules.Module;
import iekeidevelopment.orbit.EventHandler;
import net.minecraft.block.BlockState;

public class NoGhostBlocks extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> breaking = sgGeneral.add(new BoolSetting.Builder()
        .name("breaking")
        .description("Whether to apply for block breaking actions.")
        .defaultValue(true)
        .build()
    );

    public final Setting<Boolean> placing = sgGeneral.add(new BoolSetting.Builder()
        .name("placing")
        .description("Whether to apply for block placement actions.")
        .defaultValue(true)
        .build()
    );

    public NoGhostBlocks() {
        super(Categories.World, "no-ghost-blocks", "Attempts to prevent ghost blocks arising.");
    }

    @EventHandler
    private void onBreakBlock(BreakBlockEvent event) {
        if (mc.isInSingleplayer() || !breaking.get()) return;

        event.cancel();

        BlockState blockState = mc.world.getBlockState(event.blockPos);
        blockState.getBlock().onBreak(mc.world, event.blockPos, blockState, mc.player);
    }

    @EventHandler
    private void onPlaceBlock(PlaceBlockEvent event) {
        if (!placing.get()) return;

        event.cancel();
    }
}
