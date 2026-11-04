package com.iekeiclient.event.server.impl;

import com.iekeiclient.event.Event;

public class AttackEntityEvent extends Event {

	private final int entityId;

	public AttackEntityEvent(int entityId) {
		this.entityId = entityId;
	}

	public int getEntityId() {
		return entityId;
	}
}
