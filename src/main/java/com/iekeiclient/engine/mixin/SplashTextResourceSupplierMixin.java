/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixin;

import com.iekeiclient.engine.systems.config.Config;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(SplashTextResourceSupplier.class)
public abstract class SplashTextResourceSupplierMixin {
    @Unique
    private boolean override = true;
    @Unique
    private static final Random random = new Random();
    @Unique
    private final List<String> iekeiSplashes = getIekeiSplashes();

    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void onApply(CallbackInfoReturnable<SplashTextRenderer> cir) {
        if (Config.get() == null || !Config.get().titleScreenSplashes.get()) return;

        if (override) cir.setReturnValue(new SplashTextRenderer(Text.literal(iekeiSplashes.get(random.nextInt(iekeiSplashes.size())))));
        override = !override;
    }

    @Unique
    private static List<String> getIekeiSplashes() {
        return List.of(
                "Iekei on Crack!",
                "Star Iekei Client on GitHub!",
                "Based utility mod.",
                "§6MineGame159 §fbased god",
                "§4iekeiclient.com",
                "§4Iekei on Crack!",
                "§6Iekei on Crack!"
        );
    }

}
