package com.iekeiclient.addons.trouserstreak.modules;

import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.addons.trouserstreak.Trouser;

public class NoTelemetry extends Module {
    public NoTelemetry() {
        super(Trouser.Main, "NoTelemetry", "Prevents the required telemetry from being sent to Mojang. You can find blocked telemetry events in latest.log.");
    }
}