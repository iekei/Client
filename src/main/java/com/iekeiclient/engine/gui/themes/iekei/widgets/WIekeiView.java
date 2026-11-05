/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.themes.iekei.widgets;

import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.gui.themes.iekei.IekeiWidget;
import com.iekeiclient.engine.gui.widgets.containers.WView;

public class WIekeiView extends WView implements IekeiWidget {
    @Override
    protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
        if (canScroll && hasScrollBar) {
            renderer.quad(handleX(), handleY(), handleWidth(), handleHeight(), theme().scrollbarColor.get(handlePressed, handleMouseOver));
        }
    }
}
