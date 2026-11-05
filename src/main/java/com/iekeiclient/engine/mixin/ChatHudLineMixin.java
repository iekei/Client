/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixin;

import com.mojang.authlib.GameProfile;
import com.iekeiclient.engine.mixininterface.IChatHudLine;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = ChatHudLine.class)
public abstract class ChatHudLineMixin implements IChatHudLine {
    @Shadow @Final private Text content;
    @Unique private int id;
    @Unique private GameProfile sender;

    @Override
    public String iekei$getText() {
        return content.getString();
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
}
