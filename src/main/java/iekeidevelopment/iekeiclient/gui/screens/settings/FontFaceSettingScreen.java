/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.gui.screens.settings;

import iekeidevelopment.iekeiclient.gui.GuiTheme;
import iekeidevelopment.iekeiclient.gui.WindowScreen;
import iekeidevelopment.iekeiclient.gui.utils.Cell;
import iekeidevelopment.iekeiclient.gui.widgets.WLabel;
import iekeidevelopment.iekeiclient.gui.widgets.WWidget;
import iekeidevelopment.iekeiclient.gui.widgets.containers.WTable;
import iekeidevelopment.iekeiclient.gui.widgets.containers.WView;
import iekeidevelopment.iekeiclient.gui.widgets.input.WDropdown;
import iekeidevelopment.iekeiclient.gui.widgets.input.WTextBox;
import iekeidevelopment.iekeiclient.gui.widgets.pressable.WButton;
import iekeidevelopment.iekeiclient.renderer.Fonts;
import iekeidevelopment.iekeiclient.renderer.text.FontFamily;
import iekeidevelopment.iekeiclient.renderer.text.FontInfo;
import iekeidevelopment.iekeiclient.settings.FontFaceSetting;
import org.apache.commons.lang3.Strings;

import java.util.List;

public class FontFaceSettingScreen extends WindowScreen {
    private final FontFaceSetting setting;

    private WTable table;

    private WTextBox filter;
    private String filterText = "";

    public FontFaceSettingScreen(GuiTheme theme, FontFaceSetting setting) {
        super(theme, "Select Font");

        this.setting = setting;
    }

    @Override
    public void initWidgets() {
        filter = add(theme.textBox("")).expandX().widget();
        filter.setFocused(true);
        filter.action = () -> {
            filterText = filter.get().trim();

            table.clear();
            initTable();
        };

        window.view.hasScrollBar = false;

        enterAction = () -> {
            List<Cell<?>> row = table.getRow(0);
            if (row == null) return;

            WWidget widget = row.get(2).widget();
            if (widget instanceof WButton button) {
                button.action.run();
            }
        };

        WView view = add(theme.view()).expandX().widget();
        // Prevents double scrolling for view-in-view scenario
        view.maxHeight = window.view.maxHeight - 128;
        view.scrollOnlyWhenMouseOver = false;

        table = view.add(theme.table()).expandX().widget();

        initTable();
    }

    private void initTable() {
        for (FontFamily fontFamily : Fonts.FONT_FAMILIES) {
            String name = fontFamily.getName();

            WLabel item = theme.label(name);
            if (!filterText.isEmpty() && !Strings.CI.contains(name, filterText)) continue;
            table.add(item);

            WDropdown<FontInfo.Type> dropdown = table.add(theme.dropdown(FontInfo.Type.Regular)).right().widget();

            WButton select = table.add(theme.button("Select")).expandCellX().right().widget();
            select.action = () -> {
                setting.set(fontFamily.get(dropdown.get()));
                close();
            };

            table.row();
        }
    }
}
