package com.iekeiclient.ui.component.handler.impl;

import com.iekeiclient.ui.component.handler.ComponentHandler;

import net.minecraft.client.util.InputUtil.Key;

public abstract class KeybindHandler extends ComponentHandler {
	public abstract void onBinded(Key key);
}