package com.iekeiclient.addons.rejects.mixin;

import com.iekeiclient.addons.rejects.mixininterface.INoRender;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.engine.systems.modules.render.NoRender;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToastManager.class)
public class ToastManagerMixin {
    @Inject(method = "add", at = @At("HEAD"), cancellable = true)
    public void preventAdd(Toast toast, CallbackInfo ci) {
        if (((INoRender) Modules.get().get(NoRender.class)).disableToasts()) ci.cancel();
    }
}
