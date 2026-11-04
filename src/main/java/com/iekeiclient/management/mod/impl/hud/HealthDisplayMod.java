package com.iekeiclient.management.mod.impl.hud;

import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.client.RenderSkiaEvent;
import com.iekeiclient.management.mod.api.hud.SimpleHUDMod;
import com.iekeiclient.skia.font.Icon;

public class HealthDisplayMod extends SimpleHUDMod {

	public HealthDisplayMod() {
		super("mod.healthdisplay.name", "mod.healthdisplay.description", Icon.FAVORITE);
	}

	public EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> {
		this.draw();
	};

	@Override
	public String getText() {
		return (int) client.player.getHealth() + " Health";
	}

	@Override
	public String getIcon() {
		return Icon.FAVORITE;
	}
}