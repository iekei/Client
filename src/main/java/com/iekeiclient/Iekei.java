package com.iekeiclient;

import com.iekeiclient.animation.Delta;
import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.server.PacketHandler;
import com.iekeiclient.libraries.browser.JCefBrowser;
import com.iekeiclient.management.color.ColorManager;
import com.iekeiclient.management.config.ConfigManager;
import com.iekeiclient.management.hypixel.HypixelManager;
import com.iekeiclient.management.mod.ModManager;
import com.iekeiclient.management.music.MusicManager;
import com.iekeiclient.management.profile.ProfileManager;
import com.iekeiclient.management.user.UserManager;
import com.iekeiclient.management.websocket.WebSocketManager;
import com.iekeiclient.skia.font.Fonts;
import com.iekeiclient.utils.file.FileLocation;
import com.iekeiclient.utils.language.I18n;
import com.iekeiclient.utils.language.Language;

public class Iekei {

	private final static Iekei instance = new Iekei();

	private final String name = "Iekei";
	private final String version = "8.0";

	private long launchTime;

	private ModManager modManager;
	private ColorManager colorManager;
	private MusicManager musicManager;
	private ConfigManager configManager;
	private ProfileManager profileManager;
	private WebSocketManager webSocketManager;
	private UserManager userManager;
	private HypixelManager hypixelManager;

	public void start() {

		JCefBrowser.download();
		Fonts.loadAll();
		FileLocation.init();
		I18n.setLanguage(Language.ENGLISH);

		launchTime = System.currentTimeMillis();

		modManager = new ModManager();
		modManager.init();
		colorManager = new ColorManager();
		musicManager = new MusicManager();
		configManager = new ConfigManager();
		profileManager = new ProfileManager();
		webSocketManager = new WebSocketManager();
		userManager = new UserManager();
		hypixelManager = new HypixelManager();

		EventBus.getInstance().register(new IekeiHandler());
		EventBus.getInstance().register(new PacketHandler());
		EventBus.getInstance().register(new Delta());
	}

	public static Iekei getInstance() {
		return instance;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public long getLaunchTime() {
		return launchTime;
	}

	public ModManager getModManager() {
		return modManager;
	}

	public ColorManager getColorManager() {
		return colorManager;
	}

	public MusicManager getMusicManager() {
		return musicManager;
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	public ProfileManager getProfileManager() {
		return profileManager;
	}

	public WebSocketManager getWebSocketManager() {
		return webSocketManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public HypixelManager getHypixelManager() {
		return hypixelManager;
	}
}
