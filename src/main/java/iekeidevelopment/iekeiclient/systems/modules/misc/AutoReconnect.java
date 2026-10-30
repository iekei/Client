/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.systems.modules.misc;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import iekeidevelopment.iekeiclient.IekeiClient;
import iekeidevelopment.iekeiclient.events.world.ServerConnectBeginEvent;
import iekeidevelopment.iekeiclient.settings.BoolSetting;
import iekeidevelopment.iekeiclient.settings.DoubleSetting;
import iekeidevelopment.iekeiclient.settings.Setting;
import iekeidevelopment.iekeiclient.settings.SettingGroup;
import iekeidevelopment.iekeiclient.systems.modules.Categories;
import iekeidevelopment.iekeiclient.systems.modules.Module;
import iekeidevelopment.orbit.EventHandler;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;

public class AutoReconnect extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    public final Setting<Double> time = sgGeneral.add(new DoubleSetting.Builder()
        .name("delay")
        .description("The amount of seconds to wait before reconnecting to the server.")
        .defaultValue(3.5)
        .min(0)
        .decimalPlaces(1)
        .build()
    );

    public final Setting<Boolean> button = sgGeneral.add(new BoolSetting.Builder()
        .name("hide-buttons")
        .description("Will hide the buttons related to Auto Reconnect.")
        .defaultValue(false)
        .build()
    );

    public Pair<ServerAddress, ServerInfo> lastServerConnection;

    public AutoReconnect() {
        super(Categories.Misc, "auto-reconnect", "Automatically reconnects when disconnected from a server.");
        IekeiClient.EVENT_BUS.subscribe(new StaticListener());
    }

    private class StaticListener {
        @EventHandler
        private void onGameJoined(ServerConnectBeginEvent event) {
            lastServerConnection = new ObjectObjectImmutablePair<>(event.address, event.info);
        }
    }
}
