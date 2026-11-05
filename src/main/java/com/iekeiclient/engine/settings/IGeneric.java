/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.settings;

import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.WidgetScreen;
import com.iekeiclient.engine.utils.misc.ICopyable;
import com.iekeiclient.engine.utils.misc.ISerializable;

public interface IGeneric<T extends IGeneric<T>> extends ICopyable<T>, ISerializable<T> {
    WidgetScreen createScreen(GuiTheme theme, GenericSetting<T> setting);
}
