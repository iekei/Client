/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.systems.modules.misc;

import com.iekeiclient.engine.systems.modules.Categories;
import com.iekeiclient.engine.systems.modules.Module;

public class BetterBeacons extends Module {
    public BetterBeacons() {
        super(Categories.Misc, "better-beacons", "Select effects unaffected by beacon level.");
    }
}
