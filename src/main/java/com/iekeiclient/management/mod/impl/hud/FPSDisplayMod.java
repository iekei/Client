package com.iekeiclient.management.mod.impl.hud;

import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.client.RenderSkiaEvent;
import com.iekeiclient.management.mod.api.hud.SimpleHUDMod;
import com.iekeiclient.skia.font.Icon;

public class FPSDisplayMod extends SimpleHUDMod {

	public FPSDisplayMod() {
		super("mod.fpsdisplay.name", "mod.fpsdisplay.description", Icon.MONITOR);
	}

	public final EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> {
		this.draw();
	};

	@Override
	public String getText() {
		return client.getCurrentFps() + " FPS";
	}

	@Override
	public String getIcon() {
		return Icon.MONITOR;
	}
}
