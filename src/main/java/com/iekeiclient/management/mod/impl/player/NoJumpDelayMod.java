package com.iekeiclient.management.mod.impl.player;

import com.iekeiclient.management.mod.Mod;
import com.iekeiclient.management.mod.ModCategory;
import com.iekeiclient.skia.font.Icon;

public class NoJumpDelayMod extends Mod {

	private static NoJumpDelayMod instance;

	public NoJumpDelayMod() {
		super("mod.nojumpdelay.name", "mod.nojumpdelay.description", Icon.KEYBOARD_DOUBLE_ARROW_UP, ModCategory.PLAYER);

		instance = this;
	}

	public static NoJumpDelayMod getInstance() {
		return instance;
	}
}