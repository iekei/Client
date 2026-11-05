/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.systems.waypoints.events;

import com.iekeiclient.engine.systems.waypoints.Waypoint;

public record WaypointRemovedEvent(Waypoint waypoint) {
}
