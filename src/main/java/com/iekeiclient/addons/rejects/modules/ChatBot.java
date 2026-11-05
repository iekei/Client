package com.iekeiclient.addons.rejects.modules;

import com.iekeiclient.addons.rejects.IekeiRejectsAddon;
import com.iekeiclient.addons.rejects.settings.StringMapSetting;
import com.iekeiclient.engine.events.game.ReceiveMessageEvent;
import com.iekeiclient.engine.gui.utils.StarscriptTextBoxRenderer;
import com.iekeiclient.engine.settings.BoolSetting;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.settings.StringSetting;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.utils.misc.IekeiStarscript;
import com.iekeiclient.engine.utils.player.ChatUtils;
import meteordevelopment.orbit.EventHandler;
import org.meteordev.starscript.Script;
import org.meteordev.starscript.compiler.Compiler;
import org.meteordev.starscript.compiler.Parser;
import org.meteordev.starscript.utils.StarscriptError;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChatBot extends Module {

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> prefix = sgGeneral.add(new StringSetting.Builder()
            .name("prefix")
            .description("Command prefix for the bot.")
            .defaultValue("!")
            .build()
    );

    private final Setting<Boolean> help = sgGeneral.add(new BoolSetting.Builder()
            .name("help")
            .description("Add help command.")
            .defaultValue(true)
            .build()
    );

    private final Setting<Map<String, String>> commands = sgGeneral.add(new StringMapSetting.Builder()
            .name("commands")
            .description("Commands.")
            .renderer(StarscriptTextBoxRenderer.class)
            .defaultValue(new LinkedHashMap<>() {{
                put("ping", "Pong!");
                put("tps", "Current TPS: {server.tps}");
                put("time", "It's currently {server.time}");
                put("pos", "I am @ {player.pos}");
            }})
            .build()
    );

    public ChatBot() {
        super(IekeiRejectsAddon.CATEGORY, "chat-bot", "Bot which automatically responds to chat messages.");
    }

    @EventHandler
    private void onMessageRecieve(ReceiveMessageEvent event) {
        String msg = event.getMessage().getString();
        if (help.get() && msg.endsWith(prefix.get() + "help")) {
            ChatUtils.sendPlayerMsg("Available commands: " + String.join(", ", commands.get().keySet()));
            return;
        }
        for (String cmd : commands.get().keySet()) {
            if (msg.endsWith(prefix.get() + cmd)) {
                Script script = compile(commands.get().get(cmd));
                if (script == null) ChatUtils.sendPlayerMsg("An error occurred");
                try {
                    var section = IekeiStarscript.ss.run(script);
                    ChatUtils.sendPlayerMsg(section.text);
                } catch (StarscriptError e) {
                    IekeiStarscript.printChatError(e);
                    ChatUtils.sendPlayerMsg("An error occurred");
                }
                return;
            }
        }
    }

    private static Script compile(String script) {
        if (script == null) return null;
        Parser.Result result = Parser.parse(script);
        if (result.hasErrors()) {
            IekeiStarscript.printChatError(result.errors.get(0));
            return null;
        }
        return Compiler.compile(result);
    }
}
