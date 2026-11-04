package com.iekeiclient.management.mod.impl.hud;

import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.client.RenderSkiaEvent;
import com.iekeiclient.management.mod.api.hud.SimpleHUDMod;
import com.iekeiclient.skia.font.Icon;
import com.viaversion.viafabricplus.ViaFabricPlus;

public class ProtocolVersionMod extends SimpleHUDMod {

	public ProtocolVersionMod() {
		super("mod.protocolversion.name", "mod.playtimedisplay.description", Icon.BRING_YOUR_OWN_IP);
	}

	public final EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> {
		this.draw();
	};

	@Override
	public String getText() {
		return "Protocol Version: " + ViaFabricPlus.getImpl().getTargetVersion().getName();
	}

	@Override
	public String getIcon() {
		return Icon.BRING_YOUR_OWN_IP;
	}
}
