/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.themes.iekei.widgets;

import com.iekeiclient.engine.gui.themes.iekei.IekeiWidget;
import com.iekeiclient.engine.gui.widgets.WTopBar;
import com.iekeiclient.engine.utils.render.color.Color;

public class WIekeiTopBar extends WTopBar implements IekeiWidget {
    @Override
    protected Color getButtonColor(boolean pressed, boolean hovered) {
        return theme().backgroundColor.get(pressed, hovered);
    }

    @Override
    protected Color getNameColor() {
        return theme().textColor.get();
    }
}
