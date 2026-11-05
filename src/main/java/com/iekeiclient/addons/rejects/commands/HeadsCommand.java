package com.iekeiclient.addons.rejects.commands;

import com.iekeiclient.addons.rejects.gui.screens.HeadScreen;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.iekeiclient.engine.commands.Command;
import com.iekeiclient.engine.gui.GuiThemes;
import com.iekeiclient.engine.utils.Utils;
import net.minecraft.command.CommandSource;

public class HeadsCommand extends Command {

    public HeadsCommand() {
        super("heads", "Display heads gui");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(ctx -> {
            Utils.screenToOpen = new HeadScreen(GuiThemes.get());
            return SINGLE_SUCCESS;
        });

    }


}
