/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.gui.screens.settings;

import iekeidevelopment.iekeiclient.gui.GuiTheme;
import iekeidevelopment.iekeiclient.gui.screens.settings.base.CollectionListSettingScreen;
import iekeidevelopment.iekeiclient.gui.widgets.WWidget;
import iekeidevelopment.iekeiclient.settings.Setting;
import iekeidevelopment.iekeiclient.systems.modules.Module;
import iekeidevelopment.iekeiclient.systems.modules.Modules;

import java.util.List;

public class ModuleListSettingScreen extends CollectionListSettingScreen<Module> {
    public ModuleListSettingScreen(GuiTheme theme, Setting<List<Module>> setting) {
        super(theme, "Select Modules", setting, setting.get(), Modules.get().getAll());
    }

    @Override
    protected WWidget getValueWidget(Module value) {
        return theme.label(value.title);
    }

    @Override
    protected String[] getValueNames(Module value) {
        String[] names = new String[value.aliases.length + 1];
        System.arraycopy(value.aliases, 0, names, 1, value.aliases.length);
        names[0] = value.title;
        return names;
    }
}
