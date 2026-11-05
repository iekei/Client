/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixininterface;

import com.mojang.authlib.GameProfile;

public interface IChatHudLine {
    String iekei$getText();

    int iekei$getId();

    void iekei$setId(int id);

    GameProfile iekei$getSender();

    void iekei$setSender(GameProfile profile);
}
