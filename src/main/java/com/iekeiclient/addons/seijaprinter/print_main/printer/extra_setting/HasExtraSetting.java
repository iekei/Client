/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.print_main.printer.extra_setting;

import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.settings.Settings;

public interface HasExtraSetting {
    SettingGroup getSettingGroup(Settings settings);
}
