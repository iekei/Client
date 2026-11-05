package com.iekeiclient.management.mod;

import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

import com.iekeiclient.skia.font.Icon;

import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Module;

public class IekeiMod extends Mod {
	private final Module module;

	public IekeiMod(Module module) {
		super(module.title, module.description, Icon.VIEW_MODULE, toCategory(module.category));
		this.module = module;
	}

	@Override
	public void toggle() {
		module.toggle();
	}

	@Override
	public void setEnabled(boolean enabled) {
		if (enabled) {
			module.enable();
		} else {
			module.disable();
		}
	}

	@Override
	public boolean isEnabled() {
		return module.isActive();
	}

	public Module getModule() {
		return module;
	}

	public List<Setting<?>> getSettings() {
		List<Setting<?>> settings = new ArrayList<>();
		for (SettingGroup group : module.settings) {
			for (Setting<?> setting : group) {
				settings.add(setting);
			}
		}
		return settings;
	}

	private static ModCategory toCategory(Category category) {
		return switch (category.name.toLowerCase(Locale.ROOT)) {
			case "trouser-streak" -> ModCategory.TROUSER_STREAK;
			case "shama-addon" -> ModCategory.SHAMA_ADDON;
			case "seija-printer" -> ModCategory.SEIJA_PRINTER;
			case "salc1-iekei-addon" -> ModCategory.SALC1_ADDON;
			case "iekei-villager-roller" -> ModCategory.VILLAGER_ROLLER;
			case "iekei-rejects-v2" -> ModCategory.REJECTS_V2;
			case "basehuntingclient-baritone" -> ModCategory.BASE_HUNTING;
			case "combat" -> ModCategory.COMBAT;
			case "player" -> ModCategory.PLAYER;
			case "movement" -> ModCategory.MOVEMENT;
			case "render" -> ModCategory.RENDER;
			case "world" -> ModCategory.WORLD;
			default -> ModCategory.MISC;
		};
	}
}