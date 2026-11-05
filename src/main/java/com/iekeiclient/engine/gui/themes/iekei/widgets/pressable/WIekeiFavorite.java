/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.themes.iekei.widgets.pressable;

import com.iekeiclient.engine.gui.themes.iekei.IekeiWidget;
import com.iekeiclient.engine.gui.widgets.pressable.WFavorite;
import com.iekeiclient.engine.utils.render.color.Color;

public class WIekeiFavorite extends WFavorite implements IekeiWidget {
    public WIekeiFavorite(boolean checked) {
        super(checked);
    }

    @Override
    protected Color getColor() {
        return theme().favoriteColor.get();
    }
}
