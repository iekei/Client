/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixininterface;

public interface IChatHudLineVisible extends IChatHudLine {
    boolean iekei$isStartOfEntry();
    void iekei$setStartOfEntry(boolean start);
}
