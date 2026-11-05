/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.iekeiclient.engine.commands.Command;
import com.iekeiclient.engine.renderer.Fonts;
import com.iekeiclient.engine.systems.Systems;
import com.iekeiclient.engine.systems.friends.Friend;
import com.iekeiclient.engine.systems.friends.Friends;
import com.iekeiclient.engine.utils.network.Capes;
import com.iekeiclient.engine.utils.network.IekeiExecutor;
import net.minecraft.command.CommandSource;

public class ReloadCommand extends Command {
    public ReloadCommand() {
        super("reload", "Reloads many systems.");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {
            warning("Reloading systems, this may take a while.");

            Systems.load();
            Capes.init();
            Fonts.refresh();
            IekeiExecutor.execute(() -> Friends.get().forEach(Friend::updateInfo));

            return SINGLE_SUCCESS;
        });
    }
}
