/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.addons;

import com.iekeiclient.engine.IekeiClient;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;

import java.util.ArrayList;
import java.util.List;

public class AddonManager {
    public static final List<IekeiAddon> ADDONS = new ArrayList<>();

    public static void init() {
        // Iekei pseudo addon
        {
            IekeiClient.ADDON = new IekeiAddon() {
                @Override
                public void onInitialize() {}

                @Override
                public String getPackage() {
                    return "com.iekeiclient.engine";
                }

                @Override
                public String getWebsite() {
                    return "https://meteorclient.com";
                }

                @Override
                public GithubRepo getRepo() {
                    return new GithubRepo("IekeiDevelopment", "iekeiclient");
                }

                @Override
                public String getCommit() {
                    String commit = IekeiClient.MOD_META.getCustomValue(IekeiClient.MOD_ID + ":commit").getAsString();
                    return commit.isEmpty() ? null : commit;
                }
            };

            ModMetadata metadata = FabricLoader.getInstance().getModContainer(IekeiClient.MOD_ID).get().getMetadata();

            IekeiClient.ADDON.name = metadata.getName();
            IekeiClient.ADDON.authors = new String[metadata.getAuthors().size()];
            if (metadata.containsCustomValue(IekeiClient.MOD_ID + ":color")) {
                IekeiClient.ADDON.color.parse(metadata.getCustomValue(IekeiClient.MOD_ID + ":color").getAsString());
            }

            int i = 0;
            for (Person author : metadata.getAuthors()) {
                IekeiClient.ADDON.authors[i++] = author.getName();
            }

            ADDONS.add(IekeiClient.ADDON);
        }

        // Addons
        for (EntrypointContainer<IekeiAddon> entrypoint : FabricLoader.getInstance().getEntrypointContainers("iekei", IekeiAddon.class)) {
            ModMetadata metadata = entrypoint.getProvider().getMetadata();
            IekeiAddon addon;
            try {
                addon = entrypoint.getEntrypoint();
            } catch (Throwable throwable) {
                throw new RuntimeException("Exception during addon init \"%s\".".formatted(metadata.getName()), throwable);
            }

            addon.name = metadata.getName();

            if (metadata.getAuthors().isEmpty()) throw new RuntimeException("Addon \"%s\" requires at least 1 author to be defined in it's fabric.mod.json. See https://fabricmc.net/wiki/documentation:fabric_mod_json_spec".formatted(addon.name));
            addon.authors = new String[metadata.getAuthors().size()];

            if (metadata.containsCustomValue(IekeiClient.MOD_ID + ":color")) {
                addon.color.parse(metadata.getCustomValue(IekeiClient.MOD_ID + ":color").getAsString());
            }

            int i = 0;
            for (Person author : metadata.getAuthors()) {
                addon.authors[i++] = author.getName();
            }

            ADDONS.add(addon);
        }
    }
}
