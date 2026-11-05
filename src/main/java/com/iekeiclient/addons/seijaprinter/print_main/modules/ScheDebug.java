/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.print_main.modules;

import com.iekeiclient.addons.seijaprinter.Addon;
import com.iekeiclient.addons.seijaprinter.print_main.printer.util.BlockReplaceUtils;
import com.iekeiclient.engine.settings.BlockPosSetting;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.utils.player.ChatUtils;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class ScheDebug extends Module {
    public ScheDebug() {
        super(Addon.CATEGORY, "ScheDebug", "");
    }
    SettingGroup sgDefault = settings.getDefaultGroup();
    private final Setting<BlockPos> posSetting = sgDefault.add(new BlockPosSetting.Builder()
        .name("Pos")
        .build());

    @Override
    public void onActivate() {
        ChatUtils.sendMsg(Text.of(BlockReplaceUtils.INSTANCE.getScheState(posSetting.get()).getBlock().toString()));
    }
}
