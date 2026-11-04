package com.iekeiclient.management.websocket.handler.impl;

import com.google.gson.JsonObject;
import com.iekeiclient.Iekei;
import com.iekeiclient.management.hypixel.HypixelManager;
import com.iekeiclient.management.hypixel.api.HypixelUser;
import com.iekeiclient.management.websocket.handler.WebSocketHandler;
import com.iekeiclient.utils.JsonUtils;

public class HypixelStatsHandler extends WebSocketHandler {

	@Override
	public void handle(JsonObject jsonObject) {
		
		HypixelManager hypixelManager = Iekei.getInstance().getHypixelManager();

		String uuid = JsonUtils.getStringProperty(jsonObject, "uuid", "null");
		String networkLevel = JsonUtils.getStringProperty(jsonObject, "networkLevel", "-1");
		String bedwarsLevel = JsonUtils.getStringProperty(jsonObject, "bedwarsLevel", "-1");
		String winLoseRatio = JsonUtils.getStringProperty(jsonObject, "winLoseRatio", "-1");
		String finalKillDeathRatio = JsonUtils.getStringProperty(jsonObject, "finalKillDeathRatio", "-1");
		String bedsBrokeLostratio = JsonUtils.getStringProperty(jsonObject, "bedsBrokeLostRatio", "-1");
		
		hypixelManager.add(new HypixelUser(uuid, networkLevel, bedwarsLevel, winLoseRatio, finalKillDeathRatio,
				bedsBrokeLostratio));
	}
}
