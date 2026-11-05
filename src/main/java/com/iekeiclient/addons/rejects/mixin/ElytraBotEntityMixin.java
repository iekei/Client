package com.iekeiclient.addons.rejects.mixin;

import com.iekeiclient.addons.rejects.modules.bots.elytrabot.events.CancellablePlayerMoveEvent;
import com.iekeiclient.engine.IekeiClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.iekeiclient.engine.IekeiClient.mc;


@Mixin(Entity.class)
public class ElytraBotEntityMixin {
    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    private void onMove(MovementType type, Vec3d movement, CallbackInfo info) {
        if ((Object) this == mc.player) {
            if (IekeiClient.EVENT_BUS.post(CancellablePlayerMoveEvent.get(type, movement)).isCancelled())
                info.cancel();
        }
    }
}
