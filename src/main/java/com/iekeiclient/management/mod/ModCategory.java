package com.iekeiclient.management.mod;

import java.util.ArrayList;
import java.util.List;

public enum ModCategory {
	ALL("text.all"), HUD("text.hud"), COMBAT("text.combat"), PLAYER("text.player"), MOVEMENT("text.movement"),
	RENDER("text.render"), WORLD("text.world"), MISC("text.misc"),
	TROUSER_STREAK("Trouser-Streak"), SHAMA_ADDON("Shama-Addon"), SEIJA_PRINTER("Seija-Printer"),
	SALC1_ADDON("salc1-iekei-addon"), VILLAGER_ROLLER("iekei-villager-roller"),
	REJECTS_V2("iekei-rejects-v2"), BASE_HUNTING("basehuntingclient-baritone");

	private String title;

	private ModCategory(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public static List<String> getTitles() {

		List<String> output = new ArrayList<>();

		for (ModCategory c : ModCategory.values()) {
			output.add(c.getTitle());
		}

		return output;
	}
}