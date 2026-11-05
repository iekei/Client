package com.iekeiclient.addons.rejects.mixin;

import com.iekeiclient.addons.rejects.mixininterface.INoRender;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.engine.systems.modules.render.NoRender;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatInputSuggestor.class)
public class CommandSuggestorMixin {
    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
    public void onRenderCommandSuggestion(DrawContext context, int mouseX, int mouseY, CallbackInfo info) {
        if (((INoRender) Modules.get().get(NoRender.class)).noCommandSuggestions()) info.cancel();
    }
}
