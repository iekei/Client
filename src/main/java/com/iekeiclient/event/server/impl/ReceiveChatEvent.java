package com.iekeiclient.event.server.impl;

import com.iekeiclient.event.Event;

public class ReceiveChatEvent extends Event {

	private String message;

	public ReceiveChatEvent(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
