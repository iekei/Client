package com.iekeiclient;

import java.util.List;

import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.client.ClientTickEvent;
import com.iekeiclient.event.client.ServerJoinEvent;
import com.iekeiclient.event.server.impl.GameJoinEvent;
import com.iekeiclient.management.profile.Profile;

public class IekeiHandler {

	public final EventBus.EventListener<ClientTickEvent> onClientTick = event -> {
		Iekei.getInstance().getModManager().registerIekeiModules();
		Iekei.getInstance().getColorManager().onTick();
		Iekei.getInstance().getHypixelManager().update();
		Iekei.getInstance().getUserManager().update();
	};

	public final EventBus.EventListener<GameJoinEvent> onGameJoin = event -> {
		Iekei.getInstance().getHypixelManager().clear();
		Iekei.getInstance().getUserManager().clear();
	};

	public final EventBus.EventListener<ServerJoinEvent> onServerJoin = event -> {

		List<Profile> profiles = Iekei.getInstance().getProfileManager().getProfiles();

		for (Profile p : profiles) {

			String address = p.getServerIp();

			if (event.getAddress().contains(address)) {
				Iekei.getInstance().getProfileManager().load(p);
				break;
			}
		}
	};
}
