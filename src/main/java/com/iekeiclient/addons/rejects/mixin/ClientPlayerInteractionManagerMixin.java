package com.iekeiclient.addons.rejects.mixin;

import com.iekeiclient.addons.rejects.events.StopUsingItemEvent;
import com.iekeiclient.engine.IekeiClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Inject(method = "stopUsingItem", at = @At("HEAD"))
    public void onStopUsingItem(PlayerEntity player, CallbackInfo ci) {
        IekeiClient.EVENT_BUS.post(StopUsingItemEvent.get(player.getMainHandStack()));
    }
}
