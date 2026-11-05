package com.iekeiclient.addons.trouserstreak.modules;

import com.iekeiclient.engine.settings.BoolSetting;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.addons.trouserstreak.Trouser;

public class NoModDetection extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    public final Setting<Boolean> filterSign = sgGeneral.add(new BoolSetting.Builder()
            .name("Filter Sign Packets")
            .description("Filter Sign Packets.")
            .defaultValue(true)
            .build()
    );
    public final Setting<Boolean> filterAnvil = sgGeneral.add(new BoolSetting.Builder()
            .name("Filter Anvil Packets")
            .description("Filter Anvil Packets. May mess with renaming things.")
            .defaultValue(true)
            .build()
    );
    public NoModDetection() {
        super(
                Trouser.Main,
                "NoModDetection",
                "Prevents sign text from resolving client-side translation and keybind components."
        );
    }
}