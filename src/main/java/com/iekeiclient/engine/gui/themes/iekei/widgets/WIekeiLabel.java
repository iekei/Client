/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.themes.iekei.widgets;

import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.gui.themes.iekei.IekeiWidget;
import com.iekeiclient.engine.gui.widgets.WLabel;

public class WIekeiLabel extends WLabel implements IekeiWidget {
    public WIekeiLabel(String text, boolean title) {
        super(text, title);
    }

    @Override
    protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
        if (!text.isEmpty()) {
            renderer.text(text, x, y, color != null ? color : (title ? theme().titleTextColor.get() : theme().textColor.get()), title);
        }
    }
}
