/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.themes.iekei.widgets;

import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.gui.themes.iekei.IekeiWidget;
import com.iekeiclient.engine.gui.widgets.WWidget;
import com.iekeiclient.engine.gui.widgets.containers.WWindow;

public class WIekeiWindow extends WWindow implements IekeiWidget {
    public WIekeiWindow(WWidget icon, String title) {
        super(icon, title);
    }

    @Override
    protected WHeader header(WWidget icon) {
        return new WIekeiHeader(icon);
    }

    @Override
    protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
        if (expanded || animProgress > 0) {
            renderer.quad(x, y + header.height, width, height - header.height, theme().backgroundColor.get());
        }
    }

    private class WIekeiHeader extends WHeader {
        public WIekeiHeader(WWidget icon) {
            super(icon);
        }

        @Override
        protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
            renderer.quad(this, theme().accentColor.get());
        }
    }
}
