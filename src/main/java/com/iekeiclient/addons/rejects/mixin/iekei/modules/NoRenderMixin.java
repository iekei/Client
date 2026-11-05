package com.iekeiclient.addons.rejects.mixin.iekei.modules;

import com.iekeiclient.addons.rejects.mixininterface.INoRender;
import com.iekeiclient.engine.settings.BoolSetting;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.systems.modules.render.NoRender;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = NoRender.class,remap = false)
public class NoRenderMixin extends Module implements INoRender {
    @Shadow @Final private SettingGroup sgOverlay;

    private Setting<Boolean> noCommandSuggestions;
    private Setting<Boolean> disableToasts;

    public NoRenderMixin(Category category, String name, String description) {
        super(category, name, description);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        noCommandSuggestions = sgOverlay.add(new BoolSetting.Builder()
                .name("command-suggestions")
                .description("Disables command suggestions in chat.")
                .defaultValue(false)
                .build()
        );
        disableToasts = sgOverlay.add(new BoolSetting.Builder()
                .name("disable-toasts")
                .description("Disable toasts (e.g. advancements)")
                .defaultValue(false)
                .build()
        );
    }

    @Override
    public boolean noCommandSuggestions() {
        return isActive() && noCommandSuggestions.get();
    }

    @Override
    public boolean disableToasts() {
        return isActive() && disableToasts.get();
    }
}
