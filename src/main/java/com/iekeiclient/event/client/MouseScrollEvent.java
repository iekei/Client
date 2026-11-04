package com.iekeiclient.event.client;

import com.iekeiclient.event.Event;

public class MouseScrollEvent extends Event {

	private final double amount;

	public MouseScrollEvent(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}
}
