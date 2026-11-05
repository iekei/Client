/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.systems.macros;

import com.iekeiclient.engine.IekeiClient;
import com.iekeiclient.engine.events.iekei.KeyEvent;
import com.iekeiclient.engine.events.iekei.MouseClickEvent;
import com.iekeiclient.engine.systems.System;
import com.iekeiclient.engine.systems.Systems;
import com.iekeiclient.engine.utils.misc.NbtUtils;
import com.iekeiclient.engine.utils.misc.input.KeyAction;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.EventPriority;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Macros extends System<Macros> implements Iterable<Macro> {
    private List<Macro> macros = new ArrayList<>();

    public Macros() {
        super("macros");
    }

    public static Macros get() {
        return Systems.get(Macros.class);
    }

    public void add(Macro macro) {
        macros.add(macro);
        IekeiClient.EVENT_BUS.subscribe(macro);
        save();
    }

    public Macro get(String name) {
        for (Macro macro : macros) {
            if (macro.name.get().equalsIgnoreCase(name)) return macro;
        }

        return null;
    }

    public List<Macro> getAll() {
        return macros;
    }

    public void remove(Macro macro) {
        if (macros.remove(macro)) {
            IekeiClient.EVENT_BUS.unsubscribe(macro);
            save();
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onKey(KeyEvent event) {
        if (event.action == KeyAction.Release) return;

        for (Macro macro : macros) {
            if (macro.onAction(true, event.key(), event.modifiers())) return;
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onMouse(MouseClickEvent event) {
        if (event.action == KeyAction.Release) return;

        for (Macro macro : macros) {
            if (macro.onAction(false, event.button(), 0)) return;
        }
    }

    public boolean isEmpty() {
        return macros.isEmpty();
    }

    @Override
    public @NotNull Iterator<Macro> iterator() {
        return macros.iterator();
    }

    @Override
    public NbtCompound toTag() {
        NbtCompound tag = new NbtCompound();
        tag.put("macros", NbtUtils.listToTag(macros));
        return tag;
    }

    @Override
    public Macros fromTag(NbtCompound tag) {
        for (Macro macro : macros) IekeiClient.EVENT_BUS.unsubscribe(macro);

        macros = NbtUtils.listFromTag(tag.getListOrEmpty("macros"), Macro::new);

        for (Macro macro : macros) IekeiClient.EVENT_BUS.subscribe(macro);
        return this;
    }
}
