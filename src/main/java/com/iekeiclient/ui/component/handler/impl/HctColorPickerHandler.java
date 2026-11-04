package com.iekeiclient.ui.component.handler.impl;

import com.iekeiclient.libraries.material3.hct.Hct;
import com.iekeiclient.ui.component.handler.ComponentHandler;

public abstract class HctColorPickerHandler extends ComponentHandler {
	public abstract void onPicking(Hct hct);
}