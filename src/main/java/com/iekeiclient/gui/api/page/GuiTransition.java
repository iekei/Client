package com.iekeiclient.gui.api.page;

import com.iekeiclient.animation.Animation;

public abstract class GuiTransition {

	private final boolean consecutive;

	public GuiTransition(boolean consecutive) {
		this.consecutive = consecutive;
	}

	public abstract float[] onTransition(Animation animation);

	public boolean isConsecutive() {
		return consecutive;
	}
}
