/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.gui.themes.meteor.widgets.pressable;

import iekeidevelopment.iekeiclient.gui.renderer.GuiRenderer;
import iekeidevelopment.iekeiclient.gui.themes.meteor.MeteorGuiTheme;
import iekeidevelopment.iekeiclient.gui.themes.meteor.MeteorWidget;
import iekeidevelopment.iekeiclient.gui.widgets.pressable.WConfirmedMinus;
import iekeidevelopment.iekeiclient.utils.render.color.Color;

public class WMeteorConfirmedMinus extends WConfirmedMinus implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
        MeteorGuiTheme theme = theme();
        double pad = pad();
        double s = theme.scale(3);

        Color outline = theme.outlineColor.get(pressed, mouseOver);
        Color fg = pressedOnce ? theme.backgroundColor.get(pressed, mouseOver) : theme().minusColor.get();
        Color bg = pressedOnce ? theme().minusColor.get() : theme.backgroundColor.get(pressed, mouseOver);

        renderBackground(renderer, this, outline, bg);
        renderer.quad(x + pad, y + height / 2 - s / 2, width - pad * 2, s, fg);
    }
}
