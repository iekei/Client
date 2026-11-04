package com.iekeiclient.management.mod.impl.hud;

import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.client.RenderSkiaEvent;
import com.iekeiclient.management.mod.api.hud.SimpleHUDMod;
import com.iekeiclient.management.mod.settings.impl.NumberSetting;
import com.iekeiclient.skia.font.Icon;
import com.iekeiclient.utils.Multithreading;
import com.iekeiclient.utils.TimerUtils;
import com.iekeiclient.utils.server.ServerUtils;

import net.lenni0451.mcping.MCPing;

public class PingDisplayMod extends SimpleHUDMod {

	private TimerUtils timer = new TimerUtils();
	private NumberSetting refreshTimeSetting = new NumberSetting("setting.refreshtime",
			"setting.refreshtime.description", Icon.REFRESH, this, 4, 1, 20, 1);
	private long ping;
	private boolean pinging;

	public PingDisplayMod() {
		super("mod.pingdisplay.name", "mod.pingdisplay.description", Icon.WIFI);
		pinging = false;
	}

	public final EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> {
		this.draw();
	};

	private void updatePing() {

		if (timer.delay((long) (1000 * refreshTimeSetting.getValue()))) {

			if (ServerUtils.isMultiplayer()) {
				if (client.getCurrentServerEntry().ping <= 1 && !pinging) {
					Multithreading.runAsync(() -> {
						pinging = true;
						ping = MCPing.pingModern().address(client.getCurrentServerEntry().address).getSync().getPing();
						pinging = false;
					});
				} else {
					ping = client.getCurrentServerEntry().ping;
				}
			} else if (client.isIntegratedServerRunning()) {
				ping = 0;
			}

			timer.reset();
		}
	}

	@Override
	public String getText() {
		updatePing();
		return ping + " ms";
	}

	@Override
	public String getIcon() {
		return Icon.WIFI;
	}
}