package com.iekeiclient.addons.rejects.modules;

import com.iekeiclient.addons.rejects.IekeiRejectsAddon;
import com.iekeiclient.engine.events.game.GameJoinedEvent;
import com.iekeiclient.engine.events.game.OpenScreenEvent;
import com.iekeiclient.engine.events.world.TickEvent;
import com.iekeiclient.engine.settings.BoolSetting;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.gui.screen.DeathScreen;

public class GhostMode extends Module {

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> fullFood = sgGeneral.add(new BoolSetting.Builder()
        .name("full-food")
        .description("Sets the food level client-side to max.")
        .defaultValue(true)
        .build()
    );

    public GhostMode() {
        super(IekeiRejectsAddon.CATEGORY, "ghost-mode", "Allows you to keep playing after you die. Works on Forge, Fabric and Vanilla servers.");
    }

    private boolean active = false;

    @Override
    public void onDeactivate() {
        super.onDeactivate();
        active = false;
        warning("You are no longer in a ghost mode!");
        if (mc.player != null && mc.player.networkHandler != null) {
            mc.player.requestRespawn();
            info("Respawn request has been sent to the server.");
        }
    }

    @EventHandler
    private void onGameJoin(GameJoinedEvent event) {
        active = false;
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (!active) return;
        if (mc.player.getHealth() < 1f) mc.player.setHealth(20f);
        if (fullFood.get() && mc.player.getHungerManager().getFoodLevel() < 20) {
            mc.player.getHungerManager().setFoodLevel(20);
        }
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent event) {
        if (event.screen instanceof DeathScreen) {
            event.cancel();
            if (!active) {
                active = true;
                info("You are now in a ghost mode. ");
            }
        }
    }

}
