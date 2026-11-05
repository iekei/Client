/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.print_main.modules;

import com.iekeiclient.addons.seijaprinter.Addon;
import com.iekeiclient.addons.seijaprinter.print_main.hwid.Test;
import com.iekeiclient.engine.events.packets.PacketEvent;
import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.engine.utils.player.ChatUtils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.text.Text;

public class SwapTest extends Module {
    public SwapTest() {
        super(Addon.CATEGORY, "SwapTest", "");
    }
//    @EventHandler
//    public void onPacket(PacketEvent.Send e){
//        if (e.packet instanceof ClickSlotC2SPacket packet) {
//            ChatUtils.sendMsg(Text.of("Slot"+packet.getSlot()+"Button:"+packet.getButton()));
//            ChatUtils.sendMsg(Text.of(packet.getActionType()+"  "+packet.getStack().getItem()));
//
//        }
//    }

    @Override
    public void onActivate() {
        ChatUtils.sendMsg(Text.of(new Test(12).getClass().hashCode()+","));
        ChatUtils.sendMsg(Text.of(new Test(66).getClass().hashCode()+","));
        ChatUtils.sendMsg(Text.of(this.getClass().getClassLoader().toString()));
        ChatUtils.sendMsg(Text.of("Met"+Modules.get().getClass().getClassLoader().toString()));
    }
}
