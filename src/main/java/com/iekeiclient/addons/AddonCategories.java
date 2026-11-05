package com.iekeiclient.addons;

import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Modules;
import net.minecraft.item.Items;

public final class AddonCategories {
	public static final Category TROUSER_STREAK = new Category("Trouser-Streak", Items.LEATHER_LEGGINGS.getDefaultStack());
	public static final Category SHAMA_ADDON = new Category("Shama-Addon", Items.SPYGLASS.getDefaultStack());
	public static final Category SEIJA_PRINTER = new Category("Seija-Printer", Items.BLUE_BANNER.getDefaultStack());
	public static final Category SALC1_ADDON = new Category("salc1-iekei-addon", Items.CHEST.getDefaultStack());
	public static final Category VILLAGER_ROLLER = new Category("iekei-villager-roller", Items.EMERALD.getDefaultStack());
	public static final Category REJECTS_V2 = new Category("iekei-rejects-v2", Items.BARRIER.getDefaultStack());
	public static final Category BASE_HUNTING = new Category("iekei-basehuntingclient", Items.SPYGLASS.getDefaultStack());

	private AddonCategories() {
	}

	public static void registerAll() {
		Modules.registerCategory(TROUSER_STREAK);
		Modules.registerCategory(SHAMA_ADDON);
		Modules.registerCategory(SEIJA_PRINTER);
		Modules.registerCategory(SALC1_ADDON);
		Modules.registerCategory(VILLAGER_ROLLER);
		Modules.registerCategory(REJECTS_V2);
		Modules.registerCategory(BASE_HUNTING);
	}
}