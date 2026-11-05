/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.settings.impl;

import com.iekeiclient.addons.seijaprinter.Addon;
import com.iekeiclient.engine.settings.IVisible;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.Settings;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.function.Consumer;

public class SettingsSetting extends Setting<Settings> {


    public SettingsSetting(String name, String description, Settings defaultValue, Consumer<Settings> onChanged, Consumer<Setting<Settings>> onModuleActivated, IVisible visible) {
        super(name, description, defaultValue, onChanged, onModuleActivated, visible);
    }

    @Override
    protected Settings parseImpl(String str) {
        Addon.LOG.info("aadadwad");
        String[] split = str.split(" ");
        if (split.length == 2) {
            Setting<?> setting = value.get(split[0]);
            setting.parse(split[1]);
        }
        return value;
    }

    @Override
    protected boolean isValueValid(Settings value) {
        return true;
    }

    @Override
    protected void resetImpl() {
        super.resetImpl();
    }
//    @Override
//    public NbtCompound toTag() {
//        Addon.LOG.info("SaveTag1232");
//        NbtCompound tag = new NbtCompound();
//
//        tag.putString("name", name);
//        save(tag);
//
//        return tag;
//    }

    @Override
    public NbtCompound save(NbtCompound tag) {
        tag.put("settings", value.toTag());
        return tag;
    }

    @Override
    public Settings load(NbtCompound tag) {
        NbtElement settingsTag = tag.get("settings");
        Settings settings = defaultValue;
        Addon.LOG.info("Def" + defaultValue.toTag().asString());
        if (settingsTag instanceof NbtCompound) {
            settings.fromTag((NbtCompound) settingsTag);
        }
        return settings;
    }

    @Override
    public boolean wasChanged() {
        return true;
    }


    public static class Builder extends SettingBuilder<Builder, Settings, SettingsSetting> {

        public Builder() {
            super(new Settings());
        }

        @Override
        public Builder defaultValue(Settings s) {
            this.defaultValue = s;
            return this;
        }


        @Override
        public SettingsSetting build() {
            return new SettingsSetting(name, description, defaultValue, onChanged, onModuleActivated, visible);
        }
    }
}
