package com.iekeiclient.addons.trouserstreak.mixin;

import com.iekeiclient.addons.trouserstreak.events.OffGroundSpeedEvent;
import com.iekeiclient.engine.IekeiClient;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "getOffGroundSpeed", at = @At("RETURN"), cancellable = true)
    private void onGetOffGroundSpeed(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(IekeiClient.EVENT_BUS.post(OffGroundSpeedEvent.get(cir.getReturnValueF())).speed);
    }
}