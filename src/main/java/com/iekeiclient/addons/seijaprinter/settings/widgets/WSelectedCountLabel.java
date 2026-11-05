/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.settings.widgets;

import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.gui.themes.iekei.widgets.WIekeiLabel;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.Settings;

import java.util.Collection;
import java.util.Map;

public  class WSelectedCountLabel extends WIekeiLabel {
        private final Setting<?> setting;
        private int lastSize = -1;

        public WSelectedCountLabel(Setting<?> setting) {
            super("", false);

            this.setting = setting;
        }

        @Override
        protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
            int size = getSize(setting);

            if (size != lastSize) {
                if (setting.get()instanceof Settings){
                    set("(" + size + " Group)");
                }else
                    set("(" + size + " selected)");
                lastSize = size;
            }

            super.onRender(renderer, mouseX, mouseY, delta);
        }

        public static int getSize(Setting<?> setting) {
            if (setting.get() instanceof Collection<?> collection) return collection.size();
            if (setting.get() instanceof Map<?, ?> map) return map.size();
            if (setting.get() instanceof Settings) return ((Settings) setting.get()).groups.size();
            return -1;
        }
    }
