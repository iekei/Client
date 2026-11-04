package com.iekeiclient.management.websocket.packet.impl;

import com.google.gson.JsonObject;
import com.iekeiclient.management.websocket.packet.IekeiPacket;

public class SC_HypixelStatsPacket extends IekeiPacket {

	private final String uuid;
	
	public SC_HypixelStatsPacket(String uuid) {
		super("sc-hypixel-stats");
		this.uuid = uuid;
	}

	@Override
	public JsonObject toJson() {
		jsonObject.addProperty("uuid", uuid);
		return jsonObject;
	}
}
