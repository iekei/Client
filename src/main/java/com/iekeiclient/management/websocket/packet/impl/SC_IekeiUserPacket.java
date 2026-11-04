package com.iekeiclient.management.websocket.packet.impl;

import com.google.gson.JsonObject;
import com.iekeiclient.management.websocket.packet.IekeiPacket;

public class SC_IekeiUserPacket extends IekeiPacket {

	private final String uuid;
	
	public SC_IekeiUserPacket(String uuid) {
		super("sc-iekei-user");
		this.uuid = uuid;
	}

	@Override
	public JsonObject toJson() {
		jsonObject.addProperty("uuid", uuid);
		return jsonObject;
	}
}
