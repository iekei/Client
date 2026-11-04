package com.iekeiclient.management.websocket.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iekeiclient.logger.IekeiLogger;
import com.iekeiclient.management.websocket.handler.WebSocketHandler;
import com.iekeiclient.management.websocket.handler.impl.HypixelStatsHandler;
import com.iekeiclient.management.websocket.handler.impl.IekeiUserHandler;
import com.iekeiclient.utils.JsonUtils;

public class IekeiWebSocketClient extends WebSocketClient {

	private final Map<String, WebSocketHandler> handlers = new HashMap<>();
	private final Gson gson = new Gson();
	private final Runnable closeTask;

	public IekeiWebSocketClient(Map<String, String> headers, Runnable closeTask) throws URISyntaxException {
		super(new URI("ws://localhost:8080/websocket"), headers);
		this.closeTask = closeTask;
		initializeHandlers();
	}

    private void initializeHandlers() {
    	register("sc-hypixel-stats", new HypixelStatsHandler());
    	register("sc-iekei-user", new IekeiUserHandler());
    }
    
	@Override
	public void onOpen(ServerHandshake handshakedata) {
		IekeiLogger.info("API", "WebSocket connection opened");
	}

	@Override
	public void onMessage(String message) {

		JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
		
		String type = JsonUtils.getStringProperty(jsonObject, "type", "");

		WebSocketHandler handler = handlers.get(type);

		if (handler != null) {
			handler.handle(jsonObject);
		} else {
			IekeiLogger.warn("API", "No handler found for message type: " + type);
		}
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		IekeiLogger.info("API", "WebSocket connection closed: " + reason);
		closeTask.run();
	}

	@Override
	public void onError(Exception ex) {
		IekeiLogger.error("API", "WebSocket error occurred", ex);
	}

	private void register(String type, WebSocketHandler handler) {
		handlers.put(type, handler);
	}
}