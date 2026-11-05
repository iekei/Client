package com.iekeiclient.addons.trouserstreak.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.iekeiclient.engine.commands.Command;
import com.iekeiclient.engine.utils.player.ChatUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import com.iekeiclient.addons.trouserstreak.utils.MovementPatch;

public class MovementPatchCommand extends Command {
    public MovementPatchCommand() {
        super("movementpatch26.3", "Toggles tick-end injection before movement packets.");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {
            MovementPatch.toggle();

            ChatUtils.sendMsg(Text.literal("26.3 movement patch is now " + (MovementPatch.isEnabled() ? "enabled" : "disabled") + "."));

            return SINGLE_SUCCESS;
        });
        builder.then(literal("on").executes(context -> {
            MovementPatch.setEnabled(true);
            ChatUtils.sendMsg(Text.literal("26.3 movement patch enabled."));
            return SINGLE_SUCCESS;
        }));
        builder.then(literal("off").executes(context -> {
            MovementPatch.setEnabled(false);
            ChatUtils.sendMsg(Text.literal("26.3 movement patch disabled."));
            return SINGLE_SUCCESS;
        }));
        builder.then(literal("status").executes(context -> {
            ChatUtils.sendMsg(Text.literal("26.3 movement patch is currently " + (MovementPatch.isEnabled() ? "enabled" : "disabled") + "."));
            return SINGLE_SUCCESS;
        }));
    }
}