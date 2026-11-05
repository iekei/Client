/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.settings.impl;

import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.WindowScreen;
import com.iekeiclient.engine.gui.widgets.containers.WTable;
import com.iekeiclient.engine.gui.widgets.pressable.WCheckbox;
import com.iekeiclient.engine.utils.Utils;
import net.minecraft.util.math.Direction;

import java.util.List;

public class DirectionListSettingScreen extends WindowScreen {
    private final DirectionListSetting setting;
    private final WTable table;

    public DirectionListSettingScreen(GuiTheme theme, DirectionListSetting setting) {
        super(theme, "Select Gamemodes");
        this.setting = setting;
        table = super.add(theme.table()).expandX().widget();
    }

    @Override
    public void initWidgets() {
        List<Direction> gms = setting.get();
        for (Direction direction : Direction.values()) {
            table.add(theme.label(Utils.nameToTitle(direction.name()))).expandCellX();

            boolean contains = setting.get().contains(direction);
            WCheckbox checkbox = table.add(theme.checkbox(contains)).widget();
            checkbox.action = () -> {
                if (contains) {
                    gms.remove(direction);
                } else {
                    gms.add(direction);
                }
            };

            table.row();
        }
    }
}
