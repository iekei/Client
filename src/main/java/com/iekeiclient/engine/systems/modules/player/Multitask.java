/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.systems.modules.player;

import com.iekeiclient.engine.settings.BoolSetting;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.systems.modules.Categories;
import com.iekeiclient.engine.systems.modules.Module;

public class Multitask extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> attackingEntities = sgGeneral.add(new BoolSetting.Builder()
        .name("attacking-entities")
        .description("Lets you attack entities while using an item.")
        .defaultValue(true)
        .build()
    );

    public Multitask() {
        super(Categories.Player, "multitask", "Lets you use items and attack at the same time.");
    }

    public boolean attackingEntities() {
        return isActive() && attackingEntities.get();
    }
}
