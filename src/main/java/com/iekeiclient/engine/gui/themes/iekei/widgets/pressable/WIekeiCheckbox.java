/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.themes.iekei.widgets.pressable;

import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.gui.themes.iekei.IekeiGuiTheme;
import com.iekeiclient.engine.gui.themes.iekei.IekeiWidget;
import com.iekeiclient.engine.gui.widgets.pressable.WCheckbox;
import net.minecraft.util.math.MathHelper;

public class WIekeiCheckbox extends WCheckbox implements IekeiWidget {
    private double animProgress;

    public WIekeiCheckbox(boolean checked) {
        super(checked);
        animProgress = checked ? 1 : 0;
    }

    @Override
    protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
        IekeiGuiTheme theme = theme();

        animProgress += (checked ? 1 : -1) * delta * 14;
        animProgress = MathHelper.clamp(animProgress, 0, 1);

        renderBackground(renderer, this, pressed, mouseOver);

        if (animProgress > 0) {
            double cs = (width - theme.scale(2)) / 1.75 * animProgress;
            renderer.quad(x + (width - cs) / 2, y + (height - cs) / 2, cs, cs, theme.checkboxColor.get());
        }
    }
}
