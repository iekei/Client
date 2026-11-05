package com.iekeiclient.addons.rejects.mixin.iekei;

import com.iekeiclient.addons.rejects.settings.RejectsSettings;
import com.iekeiclient.engine.gui.DefaultSettingsWidgetFactory;
import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.utils.SettingsWidgetFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultSettingsWidgetFactory.class)
public abstract class DefaultSettingsWidgetFactoryMixin extends SettingsWidgetFactory {
    public DefaultSettingsWidgetFactoryMixin(GuiTheme theme) {
        super(theme);
    }

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void onInit(GuiTheme theme, CallbackInfo ci) {
        new RejectsSettings(factories, this.theme).addSettings();
    }
}
