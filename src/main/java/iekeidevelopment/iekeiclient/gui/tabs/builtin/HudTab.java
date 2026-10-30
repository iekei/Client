/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.gui.tabs.builtin;

import iekeidevelopment.iekeiclient.gui.GuiTheme;
import iekeidevelopment.iekeiclient.gui.renderer.GuiRenderer;
import iekeidevelopment.iekeiclient.gui.tabs.Tab;
import iekeidevelopment.iekeiclient.gui.tabs.TabScreen;
import iekeidevelopment.iekeiclient.gui.tabs.WindowTabScreen;
import iekeidevelopment.iekeiclient.gui.widgets.containers.WContainer;
import iekeidevelopment.iekeiclient.gui.widgets.containers.WHorizontalList;
import iekeidevelopment.iekeiclient.gui.widgets.pressable.WButton;
import iekeidevelopment.iekeiclient.gui.widgets.pressable.WCheckbox;
import iekeidevelopment.iekeiclient.systems.hud.Hud;
import iekeidevelopment.iekeiclient.systems.hud.screens.HudEditorScreen;
import iekeidevelopment.iekeiclient.utils.misc.NbtUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

import static iekeidevelopment.iekeiclient.IekeiClient.mc;

public class HudTab extends Tab {
    public HudTab() {
        super("HUD");
    }

    @Override
    public TabScreen createScreen(GuiTheme theme) {
        return new HudScreen(theme, this);
    }

    @Override
    public boolean isScreen(Screen screen) {
        return screen instanceof HudScreen;
    }

    public static class HudScreen extends WindowTabScreen {
        private WContainer settingsContainer;
        private final Hud hud;

        public HudScreen(GuiTheme theme, Tab tab) {
            super(theme, tab);

            hud = Hud.get();
            hud.settings.onActivated();
        }

        @Override
        public void initWidgets() {
            settingsContainer = add(theme.verticalList()).expandX().widget();
            settingsContainer.add(theme.settings(hud.settings)).expandX().widget();

            add(theme.horizontalSeparator()).expandX();

            WButton openEditor = add(theme.button("Edit")).expandX().widget();
            openEditor.action = () -> mc.setScreen(new HudEditorScreen(theme));

            WHorizontalList buttons = add(theme.horizontalList()).expandX().widget();
            buttons.add(theme.confirmedButton("Clear", "Confirm")).expandX().widget().action = hud::clear;
            buttons.add(theme.confirmedButton("Reset to default elements", "Confirm")).expandX().widget().action = hud::resetToDefaultElements;

            add(theme.horizontalSeparator()).expandX();

            WHorizontalList bottom = add(theme.horizontalList()).expandX().widget();

            bottom.add(theme.label("Active: "));
            WCheckbox active = bottom.add(theme.checkbox(hud.active)).expandCellX().widget();
            active.action = () -> hud.active = active.checked;

            WButton resetSettings = bottom.add(theme.button(GuiRenderer.RESET)).widget();
            resetSettings.action = hud.settings::reset;
            resetSettings.tooltip = "Reset";
        }

        @Override
        protected void onRenderBefore(DrawContext drawContext, float delta) {
            HudEditorScreen.renderElements(drawContext);
        }

        @Override
        public void tick() {
            super.tick();

            hud.settings.tick(settingsContainer, theme);
        }

        @Override
        public boolean toClipboard() {
            return NbtUtils.toClipboard(hud);
        }

        @Override
        public boolean fromClipboard() {
            return NbtUtils.fromClipboard(hud);
        }
    }
}
