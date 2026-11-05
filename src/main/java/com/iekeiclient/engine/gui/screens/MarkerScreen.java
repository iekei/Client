/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.screens;

import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.WindowScreen;
import com.iekeiclient.engine.gui.utils.Cell;
import com.iekeiclient.engine.gui.widgets.WWidget;
import com.iekeiclient.engine.gui.widgets.containers.WContainer;
import com.iekeiclient.engine.systems.modules.render.marker.BaseMarker;

public class MarkerScreen extends WindowScreen {
    private final BaseMarker marker;
    private WContainer settingsContainer;

    public MarkerScreen(GuiTheme theme, BaseMarker marker) {
        super(theme, marker.name.get());

        this.marker = marker;
    }

    @Override
    public void initWidgets() {
        // Settings
        if (!marker.settings.groups.isEmpty()) {
            settingsContainer = add(theme.verticalList()).expandX().widget();
            settingsContainer.add(theme.settings(marker.settings)).expandX();
        }

        // Custom widget
        WWidget widget = getWidget(theme);

        if (widget != null) {
            add(theme.horizontalSeparator()).expandX();
            Cell<WWidget> cell = add(widget);
            if (widget instanceof WContainer) cell.expandX();
        }
    }

    @Override
    public void tick() {
        super.tick();

        marker.settings.tick(settingsContainer, theme);
    }

    public WWidget getWidget(GuiTheme theme) {
        return null;
    }
}
