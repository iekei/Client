package com.iekeiclient.addons.rejects.utils.network;

import com.iekeiclient.engine.utils.world.TickRate;
import net.minecraft.client.network.PlayerListEntry;

import static com.iekeiclient.engine.IekeiClient.mc;

public class NetworkHelper {

    public static boolean isLagging() {
        return TickRate.INSTANCE.getTimeSinceLastTick() >= 0.8;
    }

    // can be used for ping compensation in combat modules
    public static int getCurrentPing() {
        PlayerListEntry playerListEntry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
        if (playerListEntry != null) playerListEntry.getLatency();
        return -1;
    }

    public static String getCurrentPingStr() {
        int ping = getCurrentPing();
        if (ping == -1) return "-1";
        return Integer.toString(ping);
    }

}
