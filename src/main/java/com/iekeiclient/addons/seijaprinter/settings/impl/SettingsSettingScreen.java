/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.settings.impl;

import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.WindowScreen;
import com.iekeiclient.engine.gui.widgets.containers.WContainer;
import com.iekeiclient.engine.gui.widgets.containers.WHorizontalList;

import static com.iekeiclient.engine.utils.Utils.getWindowWidth;

public class SettingsSettingScreen extends WindowScreen {
    private final SettingsSetting setting;

    private WContainer settingsContainer;
    public SettingsSettingScreen(GuiTheme theme,SettingsSetting setting){
        super(theme, setting.name);
        this.setting = setting;

    }
    @Override
    public void initWidgets() {
         //Description
        add(theme.label(setting.description, getWindowWidth() / 2.0));
        // Settings
        if (!setting.get().groups.isEmpty()) {
            settingsContainer = add(theme.verticalList()).expandX().widget();
            settingsContainer.add(theme.settings(setting.get())).expandX();
        }

        // Custom widget
//        WWidget widget = module.getWidget(theme);
//
//        if (widget != null) {
//            add(theme.horizontalSeparator()).expandX();
//            Cell<WWidget> cell = add(widget);
//            if (widget instanceof WContainer) cell.expandX();
//        }
//
        // Bottom
        WHorizontalList bottom = add(theme.horizontalList()).expandX().widget();


    }
    @Override
    public void tick() {
        super.tick();

        setting.get().tick(settingsContainer, theme);
    }

//    @Override
//    public boolean toClipboard() {
//        return NbtUtils.toClipboard(module.title, module.toTag());
//    }
//
//    @Override
//    public boolean fromClipboard() {
//        NbtCompound clipboard = NbtUtils.fromClipboard(module.toTag());
//
//        if (clipboard != null) {
//            module.fromTag(clipboard);
//            return true;
//        }
//
//        return false;
//    }

}
