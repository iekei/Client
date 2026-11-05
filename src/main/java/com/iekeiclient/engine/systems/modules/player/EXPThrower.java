/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.systems.modules.player;

import com.iekeiclient.engine.events.world.TickEvent;
import com.iekeiclient.engine.systems.modules.Categories;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.utils.player.FindItemResult;
import com.iekeiclient.engine.utils.player.InvUtils;
import com.iekeiclient.engine.utils.player.Rotations;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.item.Items;

public class EXPThrower extends Module {
    public EXPThrower() {
        super(Categories.Player, "exp-thrower", "Automatically throws XP bottles from your hotbar.");
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        FindItemResult exp = InvUtils.findInHotbar(Items.EXPERIENCE_BOTTLE);
        if (!exp.found()) return;

        Rotations.rotate(mc.player.getYaw(), 90, () -> {
            if (exp.getHand() != null) {
                mc.interactionManager.interactItem(mc.player, exp.getHand());
            }
            else {
                InvUtils.swap(exp.slot(), true);
                mc.interactionManager.interactItem(mc.player, exp.getHand());
                InvUtils.swapBack();
            }
        });
    }
}
