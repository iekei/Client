package com.iekeiclient.management.mod.impl.hud;

import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.client.RenderSkiaEvent;
import com.iekeiclient.management.mod.api.hud.SimpleHUDMod;
import com.iekeiclient.skia.font.Icon;

public class NameDisplayMod extends SimpleHUDMod {

	public NameDisplayMod() {
		super("mod.namedisplay.name", "mod.namedisplay.description", Icon.PERSON);
	}

	public final EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> {
		this.draw();
	};

	@Override
	public String getText() {
		return "Name: " + client.player.getGameProfile().getName();
	}

	@Override
	public String getIcon() {
		return Icon.PERSON;
	}
}