package com.iekeiclient.addons.rejects.modules;

import com.iekeiclient.addons.rejects.IekeiRejectsAddon;
import com.iekeiclient.engine.events.packets.PacketEvent;
import com.iekeiclient.engine.mixininterface.IPlayerInteractEntityC2SPacket;
import com.iekeiclient.engine.settings.BoolSetting;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.engine.systems.modules.combat.KillAura;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;

public class KnockbackPlus extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> ka = sgGeneral.add(new BoolSetting.Builder()
            .name("only-killaura")
            .description("Only perform more KB when using killaura.")
            .defaultValue(false)
            .build()
    );

    public KnockbackPlus() {
        super(IekeiRejectsAddon.CATEGORY, "knockback-plus", "Performs more KB when you hit your target.");
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send event) {
        if (event.packet instanceof IPlayerInteractEntityC2SPacket packet && packet.iekei$getType() == PlayerInteractEntityC2SPacket.InteractType.ATTACK) {
            Entity entity = packet.iekei$getEntity();

            if (!(entity instanceof LivingEntity) || (entity != Modules.get().get(KillAura.class).getTarget() && ka.get()))
                return;

            mc.player.networkHandler.sendPacket(new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.START_SPRINTING));
        }
    }
}
