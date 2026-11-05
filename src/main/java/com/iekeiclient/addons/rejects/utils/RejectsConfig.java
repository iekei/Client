package com.iekeiclient.addons.rejects.utils;

import com.iekeiclient.engine.IekeiClient;
import com.iekeiclient.engine.systems.System;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.engine.utils.render.prompts.OkPrompt;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RejectsConfig extends System<RejectsConfig> {
    private static final RejectsConfig INSTANCE = new RejectsConfig();

    public enum HttpAllowed {
        Everything,
        NotIekeiApi,
        NotIekeiPing,
        Nothing
    }

    public HttpAllowed httpAllowed = HttpAllowed.Everything;
    public String httpUserAgent = "Iekei Client";
    public Set<String> hiddenModules = new HashSet<>();
    public boolean loadSystemFonts = true;
    public boolean duplicateModuleNames = false;

    public RejectsConfig() {
        super("rejects-config");
        init();
        load(IekeiClient.FOLDER);
    }

    public static RejectsConfig get() {
        return INSTANCE;
    }

    public void setHiddenModules(List<Module> newList) {
        if (newList.size() < hiddenModules.size()) {
            OkPrompt.create()
                    .title("Hidden Modules")
                    .message("In order to see the modules you have removed from the list you need to restart Minecraft.")
                    .id("hidden-modules-unhide")
                    .show();
        }
        hiddenModules.clear();
        for (Module module : newList) {
            if (module == null) continue;
            if (module.isActive()) module.toggle();
            hiddenModules.add(module.name);
        }
    }

    public List<Module> getHiddenModules() {
        Modules modules = Modules.get();
        if (modules == null) return List.of();
        return hiddenModules.stream().map(modules::get).collect(Collectors.toList());
    }

    @Override
    public NbtCompound toTag() {
        NbtCompound tag = new NbtCompound();
        tag.putString("httpAllowed", httpAllowed.toString());
        tag.putString("httpUserAgent", httpUserAgent);
        tag.putBoolean("loadSystemFonts", loadSystemFonts);
        tag.putBoolean("duplicateModuleNames", duplicateModuleNames);

        NbtList modulesTag = new NbtList();
        for (String module : hiddenModules) modulesTag.add(NbtString.of(module));
        tag.put("hiddenModules", modulesTag);

        return tag;
    }

    @Override
    public RejectsConfig fromTag(NbtCompound tag) {
        httpAllowed = HttpAllowed.valueOf(tag.getString("httpAllowed", HttpAllowed.Everything.name()));
        httpUserAgent = tag.getString("httpUserAgent", "");
        loadSystemFonts = tag.getBoolean("loadSystemFonts", false);
        duplicateModuleNames = tag.getBoolean("duplicateModuleNames", false);

        NbtList valueTag = tag.getListOrEmpty("hiddenModules");
        for (NbtElement tagI : valueTag) {
            hiddenModules.add(tagI.asString().orElse(""));
        }

        return this;
    }
}
