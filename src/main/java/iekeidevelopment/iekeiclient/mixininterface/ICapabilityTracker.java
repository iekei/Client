/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.mixininterface;

public interface ICapabilityTracker {
    boolean meteor$get();

    void meteor$set(boolean state);
}
