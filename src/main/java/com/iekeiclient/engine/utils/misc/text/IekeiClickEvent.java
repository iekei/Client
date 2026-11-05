/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.utils.misc.text;

import com.iekeiclient.engine.mixin.ScreenMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.ClickEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This class does nothing except ensure that {@link ClickEvent}'s containing Iekei Client commands can only be executed if they come from the client.
 * @see ScreenMixin#onHandleBasicClickEvent(ClickEvent, MinecraftClient, Screen, CallbackInfo)
 */
public class IekeiClickEvent implements ClickEvent {
    public final String value;

    public IekeiClickEvent(String value) {
        this.value = value;
    }

    @Override
    public Action getAction() {
        return Action.RUN_COMMAND;
    }
}
