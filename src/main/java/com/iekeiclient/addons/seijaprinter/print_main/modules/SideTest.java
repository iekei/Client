/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.print_main.modules;

import com.iekeiclient.addons.seijaprinter.Addon;

import com.iekeiclient.addons.seijaprinter.settings.impl.DirectionListSetting;
import com.iekeiclient.addons.seijaprinter.settings.impl.SettingsSetting;
import com.iekeiclient.engine.settings.BoolSetting;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.settings.Settings;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.utils.player.ChatUtils;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.math.Direction;

import java.util.List;

public class SideTest extends Module {
    public SideTest() {
        super(Addon.CATEGORY, "SideTest", "");
    }

    SettingGroup sgDefault = settings.getDefaultGroup();
    Setting<Settings> settingL = sgDefault.add(new SettingsSetting.Builder()
        .name("Test")
        .defaultValue(getTestSettings())
        .build());
    Setting<List<Direction>> dirlist = sgDefault.add(new DirectionListSetting.Builder()
        .name("Test2").build());

    public static Settings getTestSettings() {
        Settings settings1 = new Settings();
        SettingGroup defaultGroup = settings1.getDefaultGroup();
        defaultGroup.add(new BoolSetting.Builder().name("NMSL").build());
        defaultGroup.add(new BoolSetting.Builder().name("NMSL2").build());
        return settings1;

    }

    @Override
    public void onActivate() {
        ChatUtils.sendMsg(Text.of(""+ Item.BLOCK_ITEMS.get(Blocks.WATER)));
//        HitResult crosshairTarget = mc.crosshairTarget;
//        if (crosshairTarget instanceof BlockHitResult) {
//            ChatUtils.sendMsg(Text.of(" "+ LiquidHelper.canPlaceLiquid(mc.player.getMainHandStack(),crosshairTarget.getPos()
//            ,((BlockHitResult) crosshairTarget).getBlockPos().offset(((BlockHitResult) crosshairTarget).getSide()),
//                ((BlockHitResult) crosshairTarget).getSide().getOpposite())));

//            BlockPos blockPos = ((BlockHitResult) crosshairTarget).getBlockPos();
//            for (Direction dir : Direction.values()) {
//                BlockState blockState = mc.world.getBlockState(blockPos);
//                ChatUtils.sendMsg(Text.of(dir.name() + "::" + blockState.isSideSolid(mc.world, blockPos, dir, SideShapeType.FULL)));


    }
}
