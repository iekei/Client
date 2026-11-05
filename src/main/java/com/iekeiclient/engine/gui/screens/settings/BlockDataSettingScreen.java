/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.screens.settings;

import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.gui.screens.settings.base.CollectionMapSettingScreen;
import com.iekeiclient.engine.gui.widgets.WWidget;
import com.iekeiclient.engine.gui.widgets.pressable.WButton;
import com.iekeiclient.engine.settings.BlockDataSetting;
import com.iekeiclient.engine.settings.IBlockData;
import com.iekeiclient.engine.utils.misc.IChangeable;
import com.iekeiclient.engine.utils.misc.ICopyable;
import com.iekeiclient.engine.utils.misc.ISerializable;
import com.iekeiclient.engine.utils.misc.Names;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.Nullable;

import static com.iekeiclient.engine.IekeiClient.mc;

public class BlockDataSettingScreen<T extends ICopyable<T> & ISerializable<T> & IChangeable & IBlockData<T>> extends CollectionMapSettingScreen<Block, T> {
    private final BlockDataSetting<T> setting;
    private boolean invalidate;

    public BlockDataSettingScreen(GuiTheme theme, BlockDataSetting<T> setting) {
        super(theme, "Configure Blocks", setting, setting.get(), Registries.BLOCK);

        this.setting = setting;
    }

    @Override
    protected boolean includeValue(Block value) {
        return value != Blocks.AIR;
    }

    @Override
    protected WWidget getValueWidget(Block block) {
        return theme.itemWithLabel(block.asItem().getDefaultStack(), Names.get(block));
    }

    @Override
    protected WWidget getDataWidget(Block block, @Nullable T blockData) {
        WButton edit = theme.button(GuiRenderer.EDIT);
        edit.action = () -> {
            T data = blockData;
            if (data == null) data = setting.defaultData.get().copy();

            mc.setScreen(data.createScreen(theme, block, setting));
            invalidate = true;
        };
        return edit;
    }

    @Override
    protected void onRenderBefore(DrawContext drawContext, float delta) {
        if (invalidate) {
            this.invalidateTable();
            invalidate = false;
        }
    }

    @Override
    protected String[] getValueNames(Block block) {
        return new String[]{
            Names.get(block),
            Registries.BLOCK.getId(block).toString()
        };
    }
}
