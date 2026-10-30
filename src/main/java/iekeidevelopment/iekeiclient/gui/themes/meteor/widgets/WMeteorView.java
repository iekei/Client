/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.gui.themes.meteor.widgets;

import iekeidevelopment.iekeiclient.gui.renderer.GuiRenderer;
import iekeidevelopment.iekeiclient.gui.themes.meteor.MeteorWidget;
import iekeidevelopment.iekeiclient.gui.widgets.containers.WView;

public class WMeteorView extends WView implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
        if (canScroll && hasScrollBar) {
            renderer.quad(handleX(), handleY(), handleWidth(), handleHeight(), theme().scrollbarColor.get(focused, handleMouseOver));
        }
    }
}
