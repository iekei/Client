/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient;

import iekeidevelopment.iekeiclient.addons.AddonManager;
import iekeidevelopment.iekeiclient.addons.MeteorAddon;
import iekeidevelopment.iekeiclient.events.game.OpenScreenEvent;
import iekeidevelopment.iekeiclient.events.meteor.KeyEvent;
import iekeidevelopment.iekeiclient.events.meteor.MouseClickEvent;
import iekeidevelopment.iekeiclient.events.world.TickEvent;
import iekeidevelopment.iekeiclient.gui.GuiThemes;
import iekeidevelopment.iekeiclient.gui.WidgetScreen;
import iekeidevelopment.iekeiclient.gui.tabs.Tabs;
import iekeidevelopment.iekeiclient.systems.Systems;
import iekeidevelopment.iekeiclient.systems.config.Config;
import iekeidevelopment.iekeiclient.systems.hud.screens.HudEditorScreen;
import iekeidevelopment.iekeiclient.systems.modules.Categories;
import iekeidevelopment.iekeiclient.systems.modules.Modules;
import iekeidevelopment.iekeiclient.systems.modules.misc.DiscordPresence;
import iekeidevelopment.iekeiclient.utils.PostInit;
import iekeidevelopment.iekeiclient.utils.PreInit;
import iekeidevelopment.iekeiclient.utils.ReflectInit;
import iekeidevelopment.iekeiclient.utils.Utils;
import iekeidevelopment.iekeiclient.utils.misc.Version;
import iekeidevelopment.iekeiclient.utils.misc.input.KeyAction;
import iekeidevelopment.iekeiclient.utils.misc.input.KeyBinds;
import iekeidevelopment.iekeiclient.utils.network.OnlinePlayers;
import iekeidevelopment.orbit.EventBus;
import iekeidevelopment.orbit.EventHandler;
import iekeidevelopment.orbit.EventPriority;
import iekeidevelopment.orbit.IEventBus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.io.File;
import java.lang.invoke.MethodHandles;

public class IekeiClient implements ClientModInitializer {
    public static final String MOD_ID = "meteor-client";
    public static final ModMetadata MOD_META;
    public static final String NAME;
    public static final Version VERSION;
    public static final String BUILD_NUMBER;

    public static IekeiClient INSTANCE;
    public static MeteorAddon ADDON;

    public static MinecraftClient mc;
    public static final IEventBus EVENT_BUS = new EventBus();
    public static final File FOLDER = FabricLoader.getInstance().getGameDir().resolve(MOD_ID).toFile();
    public static final Logger LOG;

    static {
        MOD_META = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow().getMetadata();

        NAME = MOD_META.getName();
        LOG = LoggerFactory.getLogger(NAME);

        String versionString = MOD_META.getVersion().getFriendlyString();
        if (versionString.contains("-")) versionString = versionString.split("-")[0];

        // When building and running through IntelliJ and not Gradle it doesn't replace the version so just use a dummy
        if (versionString.equals("${version}")) versionString = "0.0.0";

        VERSION = new Version(versionString);
        BUILD_NUMBER = MOD_META.getCustomValue(IekeiClient.MOD_ID + ":build_number").getAsString();
    }

    @Override
    public void onInitializeClient() {
        if (INSTANCE == null) {
            INSTANCE = this;
            return;
        }

        // Global minecraft client accessor
        mc = MinecraftClient.getInstance();

        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            LOG.info("Force loading mixins");
            MixinEnvironment.getCurrentEnvironment().audit();
        }

        LOG.info("Initializing {}", NAME);

        // Pre-load
        if (!FOLDER.exists()) {
            FOLDER.getParentFile().mkdirs();
            FOLDER.mkdir();
            Systems.addPreLoadTask(() -> Modules.get().get(DiscordPresence.class).enable());
        }

        // Register addons
        AddonManager.init();

        // Register event handlers
        AddonManager.ADDONS.forEach(addon -> {
            try {
                EVENT_BUS.registerLambdaFactory(addon.getPackage(), (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
            } catch (AbstractMethodError e) {
                throw new RuntimeException("Addon \"%s\" is too old and cannot be ran.".formatted(addon.name), e);
            }
        });

        // Register init classes
        ReflectInit.registerPackages();

        // Pre init
        ReflectInit.init(PreInit.class);

        // Register module categories
        Categories.init();

        // Load systems
        Systems.init();

        // Subscribe after systems are loaded
        EVENT_BUS.subscribe(this);

        // Initialise addons
        AddonManager.ADDONS.forEach(MeteorAddon::onInitialize);

        // Sort modules after addons have added their own
        Modules.get().sortModules();

        // Load configs
        Systems.load();

        // Post init
        ReflectInit.init(PostInit.class);

        // Save on shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            OnlinePlayers.leave();
            Systems.save();
            GuiThemes.save();
        }));
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (mc.currentScreen == null && mc.getOverlay() == null && KeyBinds.OPEN_COMMANDS.wasPressed()) {
            mc.setScreen(new ChatScreen(Config.get().prefix.get(), true));
        }
    }

    @EventHandler
    private void onKey(KeyEvent event) {
        if (event.action == KeyAction.Press && KeyBinds.OPEN_GUI.matchesKey(event.input)) {
            toggleGui();
        }
    }

    @EventHandler
    private void onMouseClick(MouseClickEvent event) {
        if (event.action == KeyAction.Press && KeyBinds.OPEN_GUI.matchesMouse(event.click)) {
            toggleGui();
        }
    }

    private void toggleGui() {
        if (Utils.canCloseGui()) mc.currentScreen.close();
        else if (Utils.canOpenGui()) Tabs.get().getFirst().openScreen(GuiThemes.get());
    }

    // Hide HUD

    private boolean wasWidgetScreen, wasHudHiddenRoot;

    @EventHandler(priority = EventPriority.LOWEST)
    private void onOpenScreen(OpenScreenEvent event) {
        if (event.screen instanceof WidgetScreen) {
            if (!wasWidgetScreen) wasHudHiddenRoot = mc.options.hudHidden;
            if (GuiThemes.get().hideHUD() || wasHudHiddenRoot) {
                // Always show the MC HUD in the HUD editor screen since people like
                // to align some items with the hotbar or chat
                mc.options.hudHidden = !(event.screen instanceof HudEditorScreen);
            }
        } else {
            if (wasWidgetScreen) mc.options.hudHidden = wasHudHiddenRoot;
            wasHudHiddenRoot = mc.options.hudHidden;
        }

        wasWidgetScreen = event.screen instanceof WidgetScreen;
    }

    public static Identifier identifier(String path) {
        return Identifier.of(IekeiClient.MOD_ID, path);
    }
}
