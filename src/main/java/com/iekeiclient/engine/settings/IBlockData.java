/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.settings;

import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.WidgetScreen;
import com.iekeiclient.engine.utils.misc.IChangeable;
import com.iekeiclient.engine.utils.misc.ICopyable;
import com.iekeiclient.engine.utils.misc.ISerializable;
import net.minecraft.block.Block;

public interface IBlockData<T extends ICopyable<T> & ISerializable<T> & IChangeable & IBlockData<T>> {
    WidgetScreen createScreen(GuiTheme theme, Block block, BlockDataSetting<T> setting);
}
