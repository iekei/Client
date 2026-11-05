/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

//package com.iekeiclient.addons.seijaprinter.print_main.printer.placedata_getter.getter;
//
//import com.iekeiclient.addons.seijaprinter.print_main.printer.placedata_getter.AbstractDataGetter;
//import com.iekeiclient.addons.seijaprinter.print_main.printer.util.records.DirData;
//import com.iekeiclient.addons.seijaprinter.print_main.printer.util.records.PlaceData;
//import com.iekeiclient.engine.settings.Setting;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.PowderSnowBlock;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.util.math.BlockPos;
//
//public class PowderSnowDataGetter extends AbstractDataGetter {
//    private static final  MinecraftClient mc = MinecraftClient.getInstance();
//    @Override
//    public PlaceData getData(BlockState needState, DirData dirData) {
//        return null;
//    }
//
//    @Override
//    public boolean isSuitable(BlockState needState, BlockPos pos) {
//        return needState.getBlock()instanceof PowderSnowBlock
//            &&mc.world.getBlockState(pos).isAir();
//    }
//    private  final Setting<Boolean> enable
//    @Override
//    public Setting[] getSettings(){
//        return
//    }
//}
