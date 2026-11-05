/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.systems.modules.player;

import com.iekeiclient.engine.systems.modules.Categories;
import com.iekeiclient.engine.systems.modules.Module;

public class Portals extends Module {
    public Portals() {
        super(Categories.Player, "portals", "Allows you to use GUIs normally while in a Nether Portal.");
    }
}
