/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.themes.iekei;

import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.gui.utils.BaseWidget;
import com.iekeiclient.engine.gui.widgets.WWidget;
import com.iekeiclient.engine.utils.render.color.Color;

public interface IekeiWidget extends BaseWidget {
    default IekeiGuiTheme theme() {
        return (IekeiGuiTheme) getTheme();
    }

    default void renderBackground(GuiRenderer renderer, WWidget widget, Color outlineColor, Color backgroundColor) {
        IekeiGuiTheme theme = theme();
        double s = theme.scale(2);

        renderer.quad(widget.x + s, widget.y + s, widget.width - s * 2, widget.height - s * 2, backgroundColor);

        renderer.quad(widget.x, widget.y, widget.width, s, outlineColor);
        renderer.quad(widget.x, widget.y + widget.height - s, widget.width, s, outlineColor);
        renderer.quad(widget.x, widget.y + s, s, widget.height - s * 2, outlineColor);
        renderer.quad(widget.x + widget.width - s, widget.y + s, s, widget.height - s * 2, outlineColor);
    }

    default void renderBackground(GuiRenderer renderer, WWidget widget, boolean pressed, boolean mouseOver) {
        IekeiGuiTheme theme = theme();
        renderBackground(renderer, widget, theme.outlineColor.get(pressed, mouseOver), theme.backgroundColor.get(pressed, mouseOver));
    }
}
