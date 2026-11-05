/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.mixin;

import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.message.LastSeenMessagesCollector;
import net.minecraft.network.message.MessageChain;
import net.minecraft.network.packet.s2c.play.CommandTreeS2CPacket;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.resource.featuretoggle.FeatureSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientPlayNetworkHandler.class)
public interface ClientPlayNetworkHandlerAccessor {
    @Accessor("chunkLoadDistance")
    int iekei$getChunkLoadDistance();

    @Accessor("messagePacker")
    MessageChain.Packer iekei$getMessagePacker();

    @Accessor("lastSeenMessagesCollector")
    LastSeenMessagesCollector iekei$getLastSeenMessagesCollector();

    @Accessor("combinedDynamicRegistries")
    DynamicRegistryManager.Immutable iekei$getCombinedDynamicRegistries();

    @Accessor("enabledFeatures")
    FeatureSet iekei$getEnabledFeatures();

    @Accessor("COMMAND_NODE_FACTORY")
    static CommandTreeS2CPacket.NodeFactory<ClientCommandSource> iekei$getCommandNodeFactory() {
        return null;
    }
}
