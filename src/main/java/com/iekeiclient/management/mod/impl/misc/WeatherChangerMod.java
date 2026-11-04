package com.iekeiclient.management.mod.impl.misc;

import java.util.Arrays;

import com.iekeiclient.management.mod.Mod;
import com.iekeiclient.management.mod.ModCategory;
import com.iekeiclient.management.mod.settings.impl.ComboSetting;
import com.iekeiclient.skia.font.Icon;

public class WeatherChangerMod extends Mod {

	private static WeatherChangerMod instance;

	private ComboSetting weatherSetting = new ComboSetting("setting.weather", "setting.weather.description", Icon.CLOUD,
			this, Arrays.asList("setting.clear", "setting.rain", "setting.storm", "setting.snow"), "setting.clear");
	
	public WeatherChangerMod() {
		super("mod.weatherchanger.name", "mod.weatherchanger.description", Icon.SUNNY, ModCategory.MISC);

		instance = this;
	}

	public static WeatherChangerMod getInstance() {
		return instance;
	}
	
	public boolean isRaining() {
		return !weatherSetting.getOption().equals("setting.clear");
	}

	public boolean isSnowing() {
		return weatherSetting.getOption().equals("setting.snow");
	}

	public boolean isThundering() {
		return weatherSetting.getOption().equals("setting.storm");
	}
}
