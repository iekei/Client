package com.iekeiclient.management.mod.impl.hud;

import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.client.RenderSkiaEvent;
import com.iekeiclient.management.mod.api.hud.SimpleHUDMod;
import com.iekeiclient.skia.font.Icon;

public class PlayerCounterMod extends SimpleHUDMod {

	public PlayerCounterMod() {
		super("mod.playercounter.name", "mod.playercounter.description", Icon.GROUPS);
	}

	public final EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> {
		this.draw();
	};

	@Override
	public String getText() {
		return "Player: " + client.world.getPlayers().size();
	}

	@Override
	public String getIcon() {
		return Icon.GROUPS;
	}
}