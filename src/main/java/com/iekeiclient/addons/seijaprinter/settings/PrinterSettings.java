/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.settings;

import com.iekeiclient.addons.seijaprinter.Addon;
import com.iekeiclient.addons.seijaprinter.settings.impl.*;
import com.iekeiclient.addons.seijaprinter.settings.widgets.input.WDoubleRangeEdit;
import com.iekeiclient.addons.seijaprinter.settings.obj.DoubleRange;
import com.iekeiclient.addons.seijaprinter.settings.widgets.input.WIekeiRangeSlider;
import com.iekeiclient.addons.seijaprinter.settings.widgets.input.WRangeSlider;
import com.iekeiclient.addons.seijaprinter.settings.widgets.WSelectedCountLabel;
import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.gui.utils.SettingsWidgetFactory;
import com.iekeiclient.engine.gui.widgets.WWidget;
import com.iekeiclient.engine.gui.widgets.containers.WContainer;
import com.iekeiclient.engine.gui.widgets.containers.WHorizontalList;
import com.iekeiclient.engine.gui.widgets.containers.WTable;
import com.iekeiclient.engine.gui.widgets.pressable.WButton;
import com.iekeiclient.engine.settings.Setting;

import java.util.Map;

import static com.iekeiclient.engine.IekeiClient.mc;

public class PrinterSettings {
    //    private final Map<Class<?>, SettingsWidgetFactory.Factory> factories;
//
    private PrinterSettings() {
    }

    public static PrinterSettings getINSTANCE() {
        return INSTANCE;
    }

    private static final PrinterSettings INSTANCE = new PrinterSettings();
//
//
//
//    public PrinterSettings(Map<Class<?>, SettingsWidgetFactory.Factory> factories, GuiTheme theme) {
//        this.factories = factories;
//        this.theme = theme;
//        INSTANCE = this;
//    }

    public void addSettings() {

        //factories.put(StringMapSetting.class, (table, setting) -> stringMapW(table, (StringMapSetting) setting));
        //factories.put(DirectionListSetting.class, (table, setting) -> dirSettingW(table, (DirectionListSetting) setting));
        //factories.put(SettingsSetting.class, (table, setting) -> settingsW(table, (SettingsSetting) setting));
        //factories.put(DoubleRangeSetting.class, (table, setting) -> doubleRangeW(table, (DoubleRangeSetting) setting));
//        factories.put(BlockReplaceSetting.class,(table, setting)-> {
//            Addon.LOG.info("Test1");
//            //blockReplaceW(table, (BlockReplaceSetting) setting);
//        });
        SettingsWidgetFactory.registerCustomFactory(DirectionListSetting.class, theme -> (
            (table, setting) ->
                dirSettingW(theme, table, (DirectionListSetting) setting)
        ));
        SettingsWidgetFactory.registerCustomFactory(SettingsSetting.class, theme -> (
            (table, setting) ->
                settingsW(theme,table, (SettingsSetting) setting)
        ));
        SettingsWidgetFactory.registerCustomFactory(DoubleRangeSetting.class, theme -> (
            (table, setting) ->
                doubleRangeW(theme,table, (DoubleRangeSetting) setting)
        ));
        SettingsWidgetFactory.registerCustomFactory(BlockReplaceSetting.class, theme -> (
            (table, setting) -> {
                blockReplaceW(theme, table, (BlockReplaceSetting) setting);
            }
        ));
    }


    private void dirSettingW(GuiTheme theme, WTable table, DirectionListSetting setting) {
        WButton button = table.add(theme.button("Select")).expandCellX().widget();
        button.action = () -> mc.setScreen(new DirectionListSettingScreen(theme, setting));

        WButton reset = table.add(theme.button(GuiRenderer.RESET)).widget();
        reset.action = setting::reset;
    }

    private void settingsW(GuiTheme theme, WTable table, SettingsSetting setting) {
        WHorizontalList widget = table.add(theme.horizontalList()).expandCellX().widget();

        WButton button = widget.add(theme.button("Edit")).expandCellX().widget();
        widget.add(new WSelectedCountLabel(setting).color(theme.textSecondaryColor()));
        button.action = () -> mc.setScreen(new SettingsSettingScreen(theme, setting));
        WButton reset = table.add(theme.button(GuiRenderer.RESET)).widget();
        reset.action = setting::reset;
    }

    private void doubleRangeW(GuiTheme theme, WTable table, DoubleRangeSetting setting) {
        DoubleRange doubleRange = setting.get();
        WDoubleRangeEdit edit = new WDoubleRangeEdit(doubleRange.value1, doubleRange.value2, setting.min, setting.max, setting.sliderMin, setting.sliderMax, setting.decimalPlaces, setting.noSlider);
        edit.theme = theme;
        table.add(edit).expandX();

        Runnable action = () -> {
            if (!setting.set(edit.getDoubleRange())) edit.set(setting.get());
        };

        if (setting.onSliderRelease) edit.actionOnRelease = action;
        else edit.action = action;

        reset(theme, table, setting, () -> edit.set(setting.get()));
    }

    private void blockReplaceW(GuiTheme theme, WTable table, BlockReplaceSetting setting) {

        WButton button = table.add(theme.button("Select")).expandCellX().widget();
        button.action = () -> mc.setScreen(new BlockReplaceSettingScreen(theme, setting));

        WButton reset = table.add(theme.button(GuiRenderer.RESET)).widget();
        reset.action = setting::reset;
    }

    private void reset(GuiTheme theme, WContainer c, Setting<?> setting, Runnable action) {
        WButton reset = c.add(theme.button(GuiRenderer.RESET)).widget();
        reset.action = () -> {
            setting.reset();
            if (action != null) action.run();
        };
    }

    public WRangeSlider rangeSlider(GuiTheme theme, double v1, double v2, double min, double max) {
        return w(theme, new WIekeiRangeSlider(v1, v2, min, max));
    }

    protected <T extends WWidget> T w(GuiTheme theme, T widget) {
        widget.theme = theme;
        return widget;
    }


}
