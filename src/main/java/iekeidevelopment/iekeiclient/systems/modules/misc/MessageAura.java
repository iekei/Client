/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.systems.modules.misc;

import iekeidevelopment.iekeiclient.events.entity.EntityAddedEvent;
import iekeidevelopment.iekeiclient.settings.BoolSetting;
import iekeidevelopment.iekeiclient.settings.Setting;
import iekeidevelopment.iekeiclient.settings.SettingGroup;
import iekeidevelopment.iekeiclient.settings.StringSetting;
import iekeidevelopment.iekeiclient.systems.friends.Friends;
import iekeidevelopment.iekeiclient.systems.modules.Categories;
import iekeidevelopment.iekeiclient.systems.modules.Module;
import iekeidevelopment.iekeiclient.utils.player.ChatUtils;
import iekeidevelopment.orbit.EventHandler;
import net.minecraft.entity.player.PlayerEntity;

public class MessageAura extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> message = sgGeneral.add(new StringSetting.Builder()
        .name("message")
        .description("The specified message sent to the player.")
        .defaultValue("Meteor on Crack!")
        .build()
    );

    private final Setting<Boolean> ignoreFriends = sgGeneral.add(new BoolSetting.Builder()
        .name("ignore-friends")
        .description("Will not send any messages to people friended.")
        .defaultValue(false)
        .build()
    );

    public MessageAura() {
        super(Categories.Misc, "message-aura", "Sends a specified message to any player that enters render distance.");
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent event) {
        if (!(event.entity instanceof PlayerEntity) || event.entity.getUuid().equals(mc.player.getUuid())) return;

        if (!ignoreFriends.get() || (ignoreFriends.get() && !Friends.get().isFriend((PlayerEntity)event.entity))) {
            ChatUtils.sendPlayerMsg("/msg " + event.entity.getName().getString() + " " + message.get());
        }
    }
}
