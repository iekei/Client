package com.iekeiclient.management.mod.impl.player;

import com.iekeiclient.management.mod.Mod;
import com.iekeiclient.management.mod.ModCategory;
import com.iekeiclient.skia.font.Icon;

public class HitDelayFixMod extends Mod {

	private static HitDelayFixMod instance;

	public HitDelayFixMod() {
		super("mod.hitdelayfix.name", "mod.hitdelayfix.description", Icon.TIMER_OFF, ModCategory.PLAYER);

		instance = this;
	}

	public static HitDelayFixMod getInstance() {
		return instance;
	}
}
