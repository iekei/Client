package com.iekeiclient.addons.rejects.mixin;

import com.iekeiclient.addons.rejects.events.TeleportParticleEvent;
import com.iekeiclient.engine.IekeiClient;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.iekeiclient.engine.IekeiClient.mc;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "handleStatus", at = @At("HEAD"))
    private void onHandleStatus(byte status, CallbackInfo ci) {
        if ((Object) this == mc.player && status == 46) {
            IekeiClient.EVENT_BUS.post(TeleportParticleEvent.get(mc.player.getX(), mc.player.getY(), mc.player.getZ()));
        }
    }
}
