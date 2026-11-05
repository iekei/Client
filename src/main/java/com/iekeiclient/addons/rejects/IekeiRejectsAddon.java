package com.iekeiclient.addons.rejects;

import com.iekeiclient.addons.rejects.commands.*;
import com.iekeiclient.addons.rejects.gui.hud.RadarHud;
import com.iekeiclient.addons.rejects.modules.*;
import com.iekeiclient.addons.rejects.modules.bots.elytrabot.AutoLand;
import com.iekeiclient.addons.rejects.modules.misc.RaidTimer;
import com.iekeiclient.addons.AddonCategories;
import com.iekeiclient.engine.addons.IekeiAddon;
import com.iekeiclient.engine.commands.Commands;
import com.iekeiclient.engine.systems.Systems;
import com.iekeiclient.engine.systems.hud.Hud;
import com.iekeiclient.engine.systems.hud.HudGroup;
import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Modules;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IekeiRejectsAddon extends IekeiAddon {
    public static final Logger LOG = LoggerFactory.getLogger("Rejects²");
    public static final Category CATEGORY = AddonCategories.REJECTS_V2;
    public static final HudGroup HUD_GROUP = new HudGroup("Rejects²");
    public static final ExecutorService MODULE_THREAD = Executors.newSingleThreadExecutor();

    @Override
    public void onInitialize() {
        LOG.info("Initializing Rejects² Addon");

        // Modules
        Modules modules = Modules.get();
        modules.add(new AimAssist());
        modules.add(new AntiBot());
        modules.add(new AntiCrash());
        modules.add(new AntiSpawnpoint());
        modules.add(new AntiVanish());
        modules.add(new ArrowDmg());
        modules.add(new AutoSell());
        modules.add(new AutoBedTrap());
        modules.add(new AutoCraft());
        modules.add(new CaptchaSolver());
        modules.add(new AutoExtinguish());
        modules.add(new AutoFarm());
        modules.add(new AutoGrind());
        modules.add(new AutoLogin());
        modules.add(new AutoLand());
        modules.add(new AutoPot());
        modules.add(new AutoSoup());
        modules.add(new AutoWither());
        modules.add(new BlockIn());
        modules.add(new Boost());
        modules.add(new ChatBot());
        modules.add(new ChestAura());
        modules.add(new ColorSigns());
        modules.add(new Confuse());
        modules.add(new CoordLogger());
        modules.add(new ExtraElytra());
        modules.add(new FullFlight());
        modules.add(new GamemodeNotifier());
        modules.add(new GhostMode());
        modules.add(new Glide());
        modules.add(new ItemGenerator());
        modules.add(new InteractionMenu());
        modules.add(new Jetpack());
        modules.add(new KnockbackPlus());
        modules.add(new LawnBot());
        modules.add(new Lavacast());
        modules.add(new MossBot());
        modules.add(new NewChunks());
        modules.add(new NoJumpDelay());
        modules.add(new ObsidianFarm());
        modules.add(new OreSim());
        modules.add(new Painter());
        modules.add(new Rendering());
        modules.add(new RoboWalk());
        modules.add(new ShieldBypass());
        modules.add(new SilentDisconnect());
        modules.add(new SkeletonESP());
        modules.add(new SoundLocator());
        modules.add(new TreeAura());
        modules.add(new VehicleOneHit());
        modules.add(new AutoEnchant());
        modules.add(new AutoRename());
        modules.add(new RaidTimer());

        // Commands
        Commands.add(new CenterCommand());
        Commands.add(new ClearChatCommand());
        Commands.add(new GhostCommand());
        Commands.add(new GiveCommand());
        Commands.add(new HeadsCommand());
        Commands.add(new KickCommand());
        Commands.add(new PanicCommand());
        Commands.add(new ReconnectCommand());
        Commands.add(new ServerCommand());
        Commands.add(new SaveSkinCommand());
        Commands.add(new SeedCommand());
        Commands.add(new SetBlockCommand());
        Commands.add(new SetVelocityCommand());
        Commands.add(new TeleportCommand());
        Commands.add(new TerrainExport());

        // HUD
        Hud hud = Systems.get(Hud.class);
        hud.register(RadarHud.INFO);

    }

    @Override
    public void onRegisterCategories() {
        // Addon categories are registered by Iekei before module initialization.
    }

    @Override
    public String getWebsite() {
        return "https://github.com/AntiCope/meteor-rejects";
    }

    public String getPackage() {
        return "com.iekeiclient.addons.rejects";
    }
}
