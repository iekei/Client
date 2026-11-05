/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixin;

import com.mojang.authlib.GameProfile;
import com.iekeiclient.engine.mixininterface.IChatHudLineVisible;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChatHudLine.Visible.class)
public abstract class ChatHudLineVisibleMixin implements IChatHudLineVisible {
    @Shadow @Final private OrderedText content;
    @Unique private int id;
    @Unique private GameProfile sender;
    @Unique private boolean startOfEntry;

    @Override
    public String iekei$getText() {
        StringBuilder sb = new StringBuilder();

        content.accept((index, style, codePoint) -> {
            sb.appendCodePoint(codePoint);
            return true;
        });

        return sb.toString();
    }

    @Override
    public int iekei$getId() {
        return id;
    }

    @Override
    public void iekei$setId(int id) {
        this.id = id;
    }

    @Override
    public GameProfile iekei$getSender() {
        return sender;
    }

    @Override
    public void iekei$setSender(GameProfile profile) {
        sender = profile;
    }

    @Override
    public boolean iekei$isStartOfEntry() {
        return startOfEntry;
    }

    @Override
    public void iekei$setStartOfEntry(boolean start) {
        startOfEntry = start;
    }
}
