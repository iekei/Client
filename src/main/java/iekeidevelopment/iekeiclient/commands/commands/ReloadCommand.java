/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import iekeidevelopment.iekeiclient.commands.Command;
import iekeidevelopment.iekeiclient.renderer.Fonts;
import iekeidevelopment.iekeiclient.systems.Systems;
import iekeidevelopment.iekeiclient.systems.friends.Friend;
import iekeidevelopment.iekeiclient.systems.friends.Friends;
import iekeidevelopment.iekeiclient.utils.network.Capes;
import iekeidevelopment.iekeiclient.utils.network.MeteorExecutor;
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
            MeteorExecutor.execute(() -> Friends.get().forEach(Friend::updateInfo));

            return SINGLE_SUCCESS;
        });
    }
}
