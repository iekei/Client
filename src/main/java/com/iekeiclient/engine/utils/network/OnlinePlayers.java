/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.utils.network;

public class OnlinePlayers {
    private static long lastPingTime;

    private OnlinePlayers() {
    }

    public static void update() {
        long time = System.currentTimeMillis();

        if (time - lastPingTime > 5 * 60 * 1000) {
            IekeiExecutor.execute(() -> Http.post("https://meteorclient.com/api/online/ping").ignoreExceptions().send());

            lastPingTime = time;
        }
    }

    public static void leave() {
        IekeiExecutor.execute(() -> Http.post("https://meteorclient.com/api/online/leave").ignoreExceptions().send());
    }
}
