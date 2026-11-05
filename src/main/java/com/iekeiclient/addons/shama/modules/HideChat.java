package com.iekeiclient.addons.shama.modules;

import com.iekeiclient.engine.systems.modules.Module;

/** HideChat — hides the chat feed and history from the screen, but you can still open chat and type. Toggle state read by ChatHudMixin. */
public class HideChat extends Module {
    public HideChat() { super(com.iekeiclient.addons.shama.ShamaAddon.MISC, "hide-chat++", "Hides chat & history but still lets you type."); }
}
