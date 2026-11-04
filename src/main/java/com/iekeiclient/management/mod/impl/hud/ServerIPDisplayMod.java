package com.iekeiclient.management.mod.impl.hud;

import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.client.RenderSkiaEvent;
import com.iekeiclient.management.mod.api.hud.SimpleHUDMod;
import com.iekeiclient.skia.font.Icon;
import com.iekeiclient.utils.server.ServerUtils;

public class ServerIPDisplayMod extends SimpleHUDMod {

	public ServerIPDisplayMod() {
		super("mod.serveripdisplay.name", "mod.serveripdisplay.description", Icon.DNS);
	}

	public final EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> {
		this.draw();
	};

	@Override
	public String getText() {

		if (ServerUtils.isSingleplayer()) {
			return "Singleplayer";
		}

		return ServerUtils.getAddress();
	}

	@Override
	public String getIcon() {
		return Icon.DNS;
	}
}