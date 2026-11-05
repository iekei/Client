package com.iekeiclient.addons.rejects.modules;

import com.iekeiclient.addons.rejects.IekeiRejectsAddon;
import com.iekeiclient.engine.systems.modules.Module;

public class SilentDisconnect extends Module {
    public SilentDisconnect() {
        super(IekeiRejectsAddon.CATEGORY, "silent-disconnect", "Won't show a disconnect screen when you disconnect.");
    }
}
