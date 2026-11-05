/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixin;

import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.authlib.yggdrasil.ProfileResult;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.SocialInteractionsManager;
import net.minecraft.client.resource.ResourceReloadLogger;
import net.minecraft.client.session.ProfileKeys;
import net.minecraft.client.session.Session;
import net.minecraft.client.session.report.AbuseReportContext;
import net.minecraft.client.texture.PlayerSkinProvider;
import net.minecraft.util.ApiServices;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.concurrent.CompletableFuture;

@Mixin(MinecraftClient.class)
public interface MinecraftClientAccessor {
    @Accessor("currentFps")
    static int iekei$getFps() {
        return 0;
    }

    @Mutable
    @Accessor("session")
    void iekei$setSession(Session session);

    @Accessor("resourceReloadLogger")
    ResourceReloadLogger iekei$getResourceReloadLogger();

    @Accessor("attackCooldown")
    int iekei$getAttackCooldown();

    @Accessor("attackCooldown")
    void iekei$setAttackCooldown(int attackCooldown);

    @Invoker("doAttack")
    boolean iekei$leftClick();

    @Mutable
    @Accessor("profileKeys")
    void iekei$setProfileKeys(ProfileKeys keys);

    @Mutable
    @Accessor("userApiService")
    void iekei$setUserApiService(UserApiService apiService);

    @Mutable
    @Accessor("skinProvider")
    void iekei$setSkinProvider(PlayerSkinProvider skinProvider);

    @Mutable
    @Accessor("socialInteractionsManager")
    void iekei$setSocialInteractionsManager(SocialInteractionsManager socialInteractionsManager);

    @Mutable
    @Accessor("abuseReportContext")
    void iekei$setAbuseReportContext(AbuseReportContext abuseReportContext);

    @Mutable
    @Accessor("gameProfileFuture")
    void iekei$setGameProfileFuture(CompletableFuture<ProfileResult> future);

    @Mutable
    @Accessor("apiServices")
    void iekei$setApiServices(ApiServices apiServices);
}
