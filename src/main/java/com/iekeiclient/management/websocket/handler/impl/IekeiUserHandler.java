package com.iekeiclient.management.websocket.handler.impl;

import com.google.gson.JsonObject;
import com.iekeiclient.Iekei;
import com.iekeiclient.management.user.UserManager;
import com.iekeiclient.management.websocket.handler.WebSocketHandler;
import com.iekeiclient.utils.JsonUtils;

public class IekeiUserHandler extends WebSocketHandler {

	@Override
	public void handle(JsonObject jsonObject) {
		
		UserManager userManager = Iekei.getInstance().getUserManager();
		
		String uuid = JsonUtils.getStringProperty(jsonObject, "uuid", "null");
		boolean isUser = JsonUtils.getBooleanProperty(jsonObject, "iekeiUser", false);
		
		if(!uuid.equals("null")) {
			userManager.add(uuid, isUser);
		}
	}
}
