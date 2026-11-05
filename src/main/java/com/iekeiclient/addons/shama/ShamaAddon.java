package com.iekeiclient.addons.shama;

import com.mojang.logging.LogUtils;
import com.iekeiclient.addons.AddonCategories;
import com.iekeiclient.engine.addons.IekeiAddon;
import com.iekeiclient.engine.systems.hud.Hud;
import com.iekeiclient.engine.systems.hud.HudGroup;
import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Modules;
import org.slf4j.Logger;

import com.iekeiclient.addons.shama.modules.BlockEntityFinder;
import com.iekeiclient.addons.shama.modules.Autoer;
import com.iekeiclient.addons.shama.modules.ChunkFinder;
import com.iekeiclient.addons.shama.modules.Miner;
import com.iekeiclient.addons.shama.modules.FastBow;
import com.iekeiclient.addons.shama.modules.FakeVisuals;
import com.iekeiclient.addons.shama.modules.FindLog;
import com.iekeiclient.addons.shama.modules.Flight;
import com.iekeiclient.addons.shama.modules.Freecam;
import com.iekeiclient.addons.shama.modules.InstantMine;
import com.iekeiclient.addons.shama.modules.HostileEsp;
import com.iekeiclient.addons.shama.modules.Invisibility;
import com.iekeiclient.addons.shama.modules.NbtAdder;
import com.iekeiclient.addons.shama.modules.NoBreakDelay;
import com.iekeiclient.addons.shama.modules.NoCooldown;
import com.iekeiclient.addons.shama.modules.OreSim;
import com.iekeiclient.addons.shama.modules.OreSpotter;
import com.iekeiclient.addons.shama.modules.SnapTap;
import com.iekeiclient.addons.shama.modules.StrongholdFinder;
import com.iekeiclient.addons.shama.modules.RareFinder;
import com.iekeiclient.addons.shama.modules.RtpFinder;
import com.iekeiclient.addons.shama.modules.PingSpoofer;
import com.iekeiclient.addons.shama.modules.ReachPlus;
import com.iekeiclient.addons.shama.modules.Speed;
import com.iekeiclient.addons.shama.modules.TunnelFinder;
import com.iekeiclient.addons.shama.modules.OceanMonumentFinder;
import com.iekeiclient.addons.shama.modules.HomeUtils;
import com.iekeiclient.addons.shama.modules.ElytraFly;
import com.iekeiclient.addons.shama.modules.Jumps;
import com.iekeiclient.addons.shama.modules.FastClimb;
import com.iekeiclient.addons.shama.modules.ClickTP;
import com.iekeiclient.addons.shama.modules.TridentBoost;
import com.iekeiclient.addons.shama.modules.SchematicBuilder;
import com.iekeiclient.addons.shama.modules.SearchMap;
import com.iekeiclient.addons.shama.modules.SignalScanner;
import com.iekeiclient.addons.shama.modules.LogoutSpots;
import com.iekeiclient.addons.shama.modules.StaffDetector;
import com.iekeiclient.addons.shama.modules.RegionMapper;
import com.iekeiclient.addons.shama.modules.MovementExtras;
import com.iekeiclient.addons.shama.modules.AnchorAura;
import com.iekeiclient.addons.shama.modules.CrystalAura;
import com.iekeiclient.addons.shama.modules.CrystalOptim;
import com.iekeiclient.addons.shama.modules.AutoWasp;
import com.iekeiclient.addons.shama.modules.SelfDefense;
import com.iekeiclient.addons.shama.modules.AutoCity;
import com.iekeiclient.addons.shama.modules.AnomalyScan;
import com.iekeiclient.addons.shama.modules.AntiAntiEsp;
import com.iekeiclient.addons.shama.modules.AutoLog;
import com.iekeiclient.addons.shama.modules.HideChat;
import com.iekeiclient.addons.shama.modules.Swarm;
import com.iekeiclient.addons.shama.modules.BowAimbot;
import com.iekeiclient.addons.shama.modules.AttributeSwap;
import com.iekeiclient.addons.shama.modules.CombatMacros;
import com.iekeiclient.addons.shama.modules.PacketLogger;
import com.iekeiclient.addons.shama.modules.WorldExtras;
import com.iekeiclient.addons.shama.modules.ChatExtras;
import com.iekeiclient.addons.shama.modules.Notifiers;
import com.iekeiclient.addons.shama.modules.CombatExtras;
import com.iekeiclient.addons.shama.modules.BaseDetector;
import com.iekeiclient.addons.shama.modules.AutoSign;
import com.iekeiclient.addons.shama.modules.BookBot;
import com.iekeiclient.addons.shama.modules.PortalInv;
import com.iekeiclient.addons.shama.modules.SwingSpeed;
import com.iekeiclient.addons.shama.modules.LagDetector;
import com.iekeiclient.addons.shama.modules.PlayerDetector;
import com.iekeiclient.addons.shama.modules.ActiveChunkDetector;
import com.iekeiclient.addons.shama.modules.DeepActivity;
import com.iekeiclient.addons.shama.modules.BlockLightEsp;
import com.iekeiclient.addons.shama.modules.AntiAfk;
import com.iekeiclient.addons.shama.modules.ReverseStep;
import com.iekeiclient.addons.shama.modules.TimeChanger;
import com.iekeiclient.addons.shama.modules.Trail;
import com.iekeiclient.addons.shama.modules.ItemHighlight;
import com.iekeiclient.addons.shama.modules.HitParticles;
import com.iekeiclient.addons.shama.modules.BreakIndicators;
import com.iekeiclient.addons.shama.modules.CameraTweaks;
import com.iekeiclient.addons.shama.modules.GeodeFinder;
import com.iekeiclient.addons.shama.modules.HoleFinder;
import com.iekeiclient.addons.shama.modules.HopperDebug;
import com.iekeiclient.addons.shama.modules.HiddenPlayerDetect;
import com.iekeiclient.addons.shama.modules.RenderDistanceBypass;
import com.iekeiclient.addons.shama.modules.ChunkLoader;
import com.iekeiclient.addons.shama.modules.ChunkReloader;
import com.iekeiclient.addons.shama.modules.RegionMapModule;
import com.iekeiclient.addons.shama.modules.ParticleEsp;
import com.iekeiclient.addons.shama.modules.SpawnClusterFinder;
import com.iekeiclient.addons.shama.modules.SpawnerFinder;
import com.iekeiclient.addons.shama.modules.SusChunkFinder;
import com.iekeiclient.addons.shama.modules.Timer;
import com.iekeiclient.addons.shama.modules.YLevelSpoofer;
import com.iekeiclient.addons.shama.commands.NbtCommand;
import com.iekeiclient.addons.shama.commands.BnbtCommand;
import com.iekeiclient.engine.commands.Commands;

public class ShamaAddon extends IekeiAddon {
    public static final Logger LOG = LogUtils.getLogger();

    // Custom category so all Shama modules group together in Iekei's GUI.
    /** Original Shama modules — everything built for Shama from scratch, including any that later absorbed a merged mode/idea from the gift batch. */
    public static final Category HUNT = AddonCategories.SHAMA_ADDON;
    public static final Category COMBAT = AddonCategories.SHAMA_ADDON;
    public static final Category MOVEMENT = AddonCategories.SHAMA_ADDON;
    public static final Category PLAYER = AddonCategories.SHAMA_ADDON;
    public static final Category MISC = AddonCategories.SHAMA_ADDON;
    public static final HudGroup HUD_GROUP = new HudGroup("Shama");

    @Override
    public void onRegisterCategories() {
        // Addon categories are registered by Iekei before module initialization.
    }

    @Override
    public void onInitialize() {
        LOG.info("Initializing Shama addon");

        Modules.get().add(new BlockEntityFinder());
        Modules.get().add(new ChunkFinder());
        Modules.get().add(new Miner());
        Modules.get().add(new Autoer());
        Modules.get().add(new HostileEsp());
        Modules.get().add(new YLevelSpoofer());
        Modules.get().add(new PingSpoofer());
        Modules.get().add(new FakeVisuals());
        Modules.get().add(new FindLog());
        Modules.get().add(new Flight());
        Modules.get().add(new FastBow());
        Modules.get().add(new NoCooldown());
        Modules.get().add(new InstantMine());
        Modules.get().add(new NoBreakDelay());
        Modules.get().add(new OreSim());
        Modules.get().add(new ReachPlus());
        Modules.get().add(new Freecam());
        Modules.get().add(new Speed());
        Modules.get().add(new SusChunkFinder());
        Modules.get().add(new SpawnerFinder());
        Modules.get().add(new SpawnClusterFinder());
        Modules.get().add(new TunnelFinder());
        Modules.get().add(new OceanMonumentFinder());
        Modules.get().add(new HomeUtils());
        Modules.get().add(new ElytraFly());
        Modules.get().add(new Jumps());
        Modules.get().add(new FastClimb());
        Modules.get().add(new ClickTP());
        Modules.get().add(new TridentBoost());
        Modules.get().add(new SchematicBuilder());
        Modules.get().add(new SearchMap());
        Modules.get().add(new SignalScanner());
        Modules.get().add(new LogoutSpots());
        Modules.get().add(new StaffDetector());
        Modules.get().add(new RegionMapper());
        Modules.get().add(new MovementExtras());
        Modules.get().add(new AnchorAura());
        Modules.get().add(new CrystalAura());
        Modules.get().add(new CrystalOptim());
        Modules.get().add(new ParticleEsp());
        Modules.get().add(new RegionMapModule());
        Modules.get().add(new ChunkLoader());
        Modules.get().add(new ChunkReloader());
        Modules.get().add(new RenderDistanceBypass());
        Modules.get().add(new HiddenPlayerDetect());
        Modules.get().add(new GeodeFinder());
        Modules.get().add(new HoleFinder());
        Modules.get().add(new HopperDebug());
        Modules.get().add(new CameraTweaks());
        Modules.get().add(new BreakIndicators());
        Modules.get().add(new HitParticles());
        Modules.get().add(new ItemHighlight());
        Modules.get().add(new Trail());
        Modules.get().add(new TimeChanger());
        Modules.get().add(new ReverseStep());
        Modules.get().add(new AntiAfk());
        Modules.get().add(new BlockLightEsp());
        Modules.get().add(new DeepActivity());
        Modules.get().add(new PlayerDetector());
        Modules.get().add(new ActiveChunkDetector());
        Modules.get().add(new LagDetector());
        Modules.get().add(new PortalInv());
        Modules.get().add(new SwingSpeed());
        Modules.get().add(new AutoSign());
        Modules.get().add(new BookBot());
        Modules.get().add(new PacketLogger());
        Modules.get().add(new WorldExtras());
        Modules.get().add(new ChatExtras());
        Modules.get().add(new Notifiers());
        Modules.get().add(new CombatExtras());
        Modules.get().add(new BaseDetector());
        Modules.get().add(new BowAimbot());
        Modules.get().add(new AttributeSwap());
        Modules.get().add(new CombatMacros());
        Modules.get().add(new AutoWasp());
        Modules.get().add(new SelfDefense());
        Modules.get().add(new AutoCity());
        Modules.get().add(new AnomalyScan());
        Modules.get().add(new AntiAntiEsp());
        Modules.get().add(new AutoLog());
        Modules.get().add(new HideChat());
        Modules.get().add(new Swarm());

        Hud.get().register(com.iekeiclient.addons.shama.hud.RadarHud.INFO);
        Hud.get().register(com.iekeiclient.addons.shama.hud.LightHud.INFO);
        Hud.get().register(com.iekeiclient.addons.shama.hud.RegionHud.INFO);
        Hud.get().register(com.iekeiclient.addons.shama.hud.BaseRadarHud.INFO);
        Modules.get().add(new Invisibility());
        Modules.get().add(new OreSpotter());
        Modules.get().add(new SnapTap());
        Modules.get().add(new StrongholdFinder());
        Modules.get().add(new RareFinder());
        Modules.get().add(new RtpFinder());
        Modules.get().add(new NbtAdder());
        Modules.get().add(new Timer());

        Commands.add(new NbtCommand());
        Commands.add(new BnbtCommand());
    }

    @Override
    public String getPackage() {
        return "com.iekeiclient.addons.shama";
    }
}
