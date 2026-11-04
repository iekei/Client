package com.iekeiclient.event.client;

import com.iekeiclient.event.Event;

public class KeyEvent extends Event {

	private final int keyCode;

	public KeyEvent(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return keyCode;
	}
}
