package com.iekeiclient.addons.bhclient.hud;

import com.iekeiclient.addons.bhclient.IekeiBaseHuntingAddon;
import com.iekeiclient.addons.bhclient.modules.IekeiBaseHunter;
import com.iekeiclient.addons.bhclient.modules.NewChunks;
import com.iekeiclient.addons.bhclient.systems.PlayerTracker;
import com.iekeiclient.addons.bhclient.systems.StashManager;
import com.iekeiclient.addons.bhclient.utils.ServerUtils;
import com.iekeiclient.engine.settings.BoolSetting;
import com.iekeiclient.engine.settings.ColorSetting;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.systems.hud.HudElement;
import com.iekeiclient.engine.systems.hud.HudElementInfo;
import com.iekeiclient.engine.systems.hud.HudRenderer;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.engine.utils.render.color.SettingColor;
import net.minecraft.item.Items;

import static com.iekeiclient.engine.IekeiClient.mc;

import java.util.ArrayList;
import java.util.List;

/** Compact status readout so the important numbers are visible while flying. */
public class BaseHuntingHud extends HudElement {
    public static final HudElementInfo<BaseHuntingHud> INFO = new HudElementInfo<>(
        IekeiBaseHuntingAddon.HUD_GROUP,
        "base-hunting",
        "Shows new chunk, stash and autopilot status.",
        BaseHuntingHud::new
    );

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> showServer = sgGeneral.add(new BoolSetting.Builder()
        .name("show-server")
        .description("Show the server address.")
        .defaultValue(false)
        .build()
    );

    private final Setting<Boolean> showNewChunks = sgGeneral.add(new BoolSetting.Builder()
        .name("show-new-chunks")
        .description("Show how many new chunks have been found.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Boolean> showStashes = sgGeneral.add(new BoolSetting.Builder()
        .name("show-stashes")
        .description("Show how many stashes have been found.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Boolean> showPlayers = sgGeneral.add(new BoolSetting.Builder()
        .name("show-players")
        .description("Show how many players have been recorded.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Boolean> showAutopilot = sgGeneral.add(new BoolSetting.Builder()
        .name("show-autopilot")
        .description("Show autopilot destination and distance.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Boolean> showFireworks = sgGeneral.add(new BoolSetting.Builder()
        .name("show-fireworks")
        .description("Show the remaining firework count.")
        .defaultValue(true)
        .build()
    );

    private final Setting<SettingColor> labelColor = sgGeneral.add(new ColorSetting.Builder()
        .name("label-color")
        .description("Colour of the labels.")
        .defaultValue(new SettingColor(150, 150, 150))
        .build()
    );

    private final Setting<SettingColor> valueColor = sgGeneral.add(new ColorSetting.Builder()
        .name("value-color")
        .description("Colour of the values.")
        .defaultValue(new SettingColor(0, 200, 255))
        .build()
    );

    private final Setting<Boolean> shadow = sgGeneral.add(new BoolSetting.Builder()
        .name("shadow")
        .description("Draw a shadow behind the text.")
        .defaultValue(true)
        .build()
    );

    public BaseHuntingHud() {
        super(INFO);
    }

    @Override
    public void render(HudRenderer renderer) {
        List<String> lines = buildLines();

        double width = 0;
        for (String line : lines) width = Math.max(width, renderer.textWidth(line, shadow.get()));

        setSize(width, renderer.textHeight(shadow.get()) * lines.size());

        double y = this.y;

        for (String line : lines) {
            int separator = line.indexOf(':');

            if (separator < 0) {
                renderer.text(line, x, y, valueColor.get(), shadow.get());
            } else {
                String label = line.substring(0, separator + 1);
                String value = line.substring(separator + 1);

                double labelWidth = renderer.text(label, x, y, labelColor.get(), shadow.get());
                renderer.text(value, x + labelWidth, y, valueColor.get(), shadow.get());
            }

            y += renderer.textHeight(shadow.get());
        }
    }

    private List<String> buildLines() {
        List<String> lines = new ArrayList<>();

        if (showServer.get()) lines.add("Server: " + ServerUtils.serverAddress());
        lines.add("Position: " + ServerUtils.coordinates());

        NewChunks newChunks = Modules.get().get(NewChunks.class);
        if (showNewChunks.get() && newChunks != null && newChunks.isActive()) {
            lines.add("New chunks: %d".formatted(newChunks.getTrackedCount()));
        }

        StashManager stashManager = StashManager.get();
        if (showStashes.get() && stashManager != null) {
            lines.add("Stashes: %d".formatted(stashManager.forCurrentServer().size()));
        }

        PlayerTracker tracker = PlayerTracker.get();
        if (showPlayers.get() && tracker != null) {
            lines.add("Players: %d".formatted(tracker.forCurrentServer().size()));
        }

        IekeiBaseHunter hunter = Modules.get().get(IekeiBaseHunter.class);
        if (showAutopilot.get() && hunter != null && hunter.isActive()) {
            var destination = hunter.getCurrentDestination();

            if (destination != null) {
                double dx = destination.getX() - mc.player.getX();
                double dz = destination.getZ() - mc.player.getZ();
                lines.add("Destination: %d, %d".formatted(destination.getX(), destination.getZ()));
                lines.add("Remaining: %d blocks".formatted((int) Math.sqrt(dx * dx + dz * dz)));
            }

            lines.add("Status: " + (hunter.isPaused() ? "paused" : "flying"));
            lines.add("Flown: %,d blocks".formatted((long) hunter.getTravelledBlocks()));
        }

        if (showFireworks.get() && mc.player != null) {
            lines.add("Fireworks: %d".formatted(countFireworks()));
        }

        return lines;
    }

    private int countFireworks() {
        int total = 0;

        for (int slot = 0; slot < mc.player.getInventory().size(); slot++) {
            if (mc.player.getInventory().getStack(slot).getItem() == Items.FIREWORK_ROCKET) {
                total += mc.player.getInventory().getStack(slot).getCount();
            }
        }

        return total;
    }
}
