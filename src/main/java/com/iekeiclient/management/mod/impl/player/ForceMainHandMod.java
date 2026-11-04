package com.iekeiclient.management.mod.impl.player;

import java.util.Arrays;

import com.iekeiclient.management.mod.Mod;
import com.iekeiclient.management.mod.ModCategory;
import com.iekeiclient.management.mod.settings.impl.ComboSetting;
import com.iekeiclient.skia.font.Icon;

public class ForceMainHandMod extends Mod {

	private static ForceMainHandMod instance;
	private ComboSetting typeSetting = new ComboSetting("setting.type", "setting.type.description", Icon.FRONT_HAND,
			this, Arrays.asList("setting.left", "setting.right"), "setting.right");

	public ForceMainHandMod() {
		super("mod.forcemainhand.name", "mod.forcemainhand.description", Icon.FRONT_HAND, ModCategory.PLAYER);
		instance = this;
	}

	public static ForceMainHandMod getInstance() {
		return instance;
	}
	
	public boolean isRightHand() {
		return typeSetting.getOption().contains("right");
	}
}
