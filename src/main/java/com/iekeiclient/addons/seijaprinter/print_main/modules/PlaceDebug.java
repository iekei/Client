/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.print_main.modules;

import com.iekeiclient.addons.seijaprinter.Addon;
import com.iekeiclient.engine.events.packets.PacketEvent;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.utils.player.ChatUtils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;

public class PlaceDebug extends Module {

    public PlaceDebug() {
        super(Addon.CATEGORY, "PlaceDebug", "Test");

    }
    @EventHandler
    public void onPacket(PacketEvent.Send send){
        if (send.packet instanceof PlayerInteractBlockC2SPacket) {
            PlayerInteractBlockC2SPacket packet = (PlayerInteractBlockC2SPacket) send.packet;
            BlockHitResult bhr = packet.getBlockHitResult();

            ChatUtils.sendMsg(Text.of(bhr.getBlockPos()+"  Side:"+bhr.getSide()+" Vec:"+bhr.getPos()+"Type:"+bhr.getType()));
        }
    }
}
