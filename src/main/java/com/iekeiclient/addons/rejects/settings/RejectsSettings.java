package com.iekeiclient.addons.rejects.settings;

import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.gui.utils.SettingsWidgetFactory;
import com.iekeiclient.engine.gui.widgets.containers.WTable;
import com.iekeiclient.engine.gui.widgets.pressable.WButton;

import java.util.Map;

import static com.iekeiclient.engine.IekeiClient.mc;

public class RejectsSettings {
    private final Map<Class<?>, SettingsWidgetFactory.Factory> factories;

    private final GuiTheme theme;

    public RejectsSettings(Map<Class<?>, SettingsWidgetFactory.Factory> factories, GuiTheme theme) {
        this.factories = factories;
        this.theme = theme;
    }

    public void addSettings() {
        factories.put(StringMapSetting.class, (table, setting) -> stringMapW(table, (StringMapSetting) setting));
        factories.put(GameModeListSetting.class, (table, setting) -> gameModeListW(table, (GameModeListSetting) setting));
    }

    private void stringMapW(WTable table, StringMapSetting setting) {
        WTable wtable = table.add(theme.table()).expandX().widget();
        StringMapSetting.fillTable(theme, wtable, setting);
    }

    private void gameModeListW(WTable table, GameModeListSetting setting) {
        WButton button = table.add(theme.button("Select")).expandCellX().widget();
        button.action = () -> mc.setScreen(new GameModeListSettingScreen(theme, setting));

        WButton reset = table.add(theme.button(GuiRenderer.RESET)).widget();
        reset.action = setting::reset;
    }
}
