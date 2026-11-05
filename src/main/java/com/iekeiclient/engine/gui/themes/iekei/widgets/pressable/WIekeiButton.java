/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.themes.iekei.widgets.pressable;

import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.gui.renderer.packer.GuiTexture;
import com.iekeiclient.engine.gui.themes.iekei.IekeiGuiTheme;
import com.iekeiclient.engine.gui.themes.iekei.IekeiWidget;
import com.iekeiclient.engine.gui.widgets.pressable.WButton;

public class WIekeiButton extends WButton implements IekeiWidget {
    public WIekeiButton(String text, GuiTexture texture) {
        super(text, texture);
    }

    @Override
    protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
        IekeiGuiTheme theme = theme();
        double pad = pad();

        renderBackground(renderer, this, pressed, mouseOver);

        if (text != null) {
            renderer.text(text, x + width / 2 - textWidth / 2, y + pad, theme.textColor.get(), false);
        }
        else {
            double ts = theme.textHeight();
            renderer.quad(x + width / 2 - ts / 2, y + pad, ts, ts, texture, theme.textColor.get());
        }
    }
}
