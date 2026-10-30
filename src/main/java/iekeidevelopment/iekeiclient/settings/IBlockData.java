/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.settings;

import iekeidevelopment.iekeiclient.gui.GuiTheme;
import iekeidevelopment.iekeiclient.gui.WidgetScreen;
import iekeidevelopment.iekeiclient.utils.misc.IChangeable;
import iekeidevelopment.iekeiclient.utils.misc.ICopyable;
import iekeidevelopment.iekeiclient.utils.misc.ISerializable;
import net.minecraft.block.Block;

public interface IBlockData<T extends ICopyable<T> & ISerializable<T> & IChangeable & IBlockData<T>> {
    WidgetScreen createScreen(GuiTheme theme, Block block, BlockDataSetting<T> setting);
}
