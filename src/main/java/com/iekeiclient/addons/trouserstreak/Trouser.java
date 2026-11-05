package com.iekeiclient.addons.trouserstreak;

import com.iekeiclient.engine.addons.IekeiAddon;
import com.iekeiclient.engine.commands.Commands;
import com.iekeiclient.engine.systems.hud.Hud;
import com.iekeiclient.engine.systems.hud.HudGroup;
import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.addons.AddonCategories;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.iekeiclient.addons.trouserstreak.commands.*;
import com.iekeiclient.addons.trouserstreak.hud.*;
import com.iekeiclient.addons.trouserstreak.modules.*;

public class Trouser extends IekeiAddon {
        public static final Logger LOG = LoggerFactory.getLogger(Trouser.class);
        public static final Category Main = AddonCategories.TROUSER_STREAK;
        public static final Category baseHunting = AddonCategories.TROUSER_STREAK;
        public static final Category operator = AddonCategories.TROUSER_STREAK;

        public static final HudGroup HUD_GROUP = new HudGroup("Trouser Streak");

        @Override
        public void onInitialize() {
                LOG.info("Initializing PantsMod!");
                Modules.get().add(new ActivatedSpawnerDetector());
                Modules.get().add(new AdvancedItemESP());
                Modules.get().add(new AimBot());
                Modules.get().add(new AirstrikePlus());
                Modules.get().add(new AnHero());
                Modules.get().add(new ArmorStandImages());
                Modules.get().add(new AttributeSwap());
                Modules.get().add(new AutoCommand());
                Modules.get().add(new AutoDisplays());
                Modules.get().add(new AutoDrop());
                Modules.get().add(new AutoLavaCaster());
                Modules.get().add(new AutoMountain());
                Modules.get().add(new AutoNames());
                Modules.get().add(new AutoScoreboard());
                Modules.get().add(new AutoStaircase());
                Modules.get().add(new AutoTexts());
                Modules.get().add(new AutoTnt());
                Modules.get().add(new AutoTitles());
                Modules.get().add(new AutoWither());
                Modules.get().add(new BaseFinder());
                Modules.get().add(new BetterAutoSign());
                Modules.get().add(new BetterScaffold());
                Modules.get().add(new BlockListMineCommand());
                Modules.get().add(new BoatKill());
                Modules.get().add(new BoatNoclip());
                Modules.get().add(new BoomPlus());
                Modules.get().add(new BungeeSpoofer());
                Modules.get().add(new CaveDisturbanceDetector());
                Modules.get().add(new CollectibleESP());
                Modules.get().add(new CrossbowMachineGun());
                Modules.get().add(new EffectsGiver());
                Modules.get().add(new ExplosionAura());
                Modules.get().add(new FreeBoatRide());
                Modules.get().add(new FlightAntikick());
                Modules.get().add(new HandOfGod());
                Modules.get().add(new HandSwapper());
                Modules.get().add(new HoleAndTunnelAndStairsESP());
                Modules.get().add(new InfiniteElytra());
                Modules.get().add(new InfiniteReach());
                Modules.get().add(new InfiniteTools());
                Modules.get().add(new InstaMineNuker());
                Modules.get().add(new InstaSafetyBox());
                Modules.get().add(new ItemTractorBeam());
                Modules.get().add(new KnockBackModifier());
                Modules.get().add(new LavaAura());
                Modules.get().add(new LoadingTerrainDisconnect());
                Modules.get().add(new LodestoneCoords());
                Modules.get().add(new MaceKill());
                Modules.get().add(new MobGearESP());
                Modules.get().add(new MultiUse());
                Modules.get().add(new MultiverseAnnihilator());
                Modules.get().add(new NbtEditor());
                Modules.get().add(new NewerNewChunks());
                Modules.get().add(new NoModDetection());
                Modules.get().add(new NoSpawnerDetector());
                Modules.get().add(new NoTelemetry());
                Modules.get().add(new OnlinePlayerActivityDetector());
                Modules.get().add(new PacketDelay());
                Modules.get().add(new PlayerAlarms());
                Modules.get().add(new PortalPatternFinder());
                Modules.get().add(new PotESP());
                Modules.get().add(new ProjectileLauncher());
                Modules.get().add(new ProjectileTeleport());
                Modules.get().add(new RedstoneNuker());
                Modules.get().add(new RemoteEnderChest());
                Modules.get().add(new SpearKill());
                Modules.get().add(new StatsHud());
                Modules.get().add(new StorageLooter());
                Modules.get().add(new SuperInstaMine());
                Modules.get().add(new Teleport());
                Modules.get().add(new TPAura());
                Modules.get().add(new TPFly());
                Modules.get().add(new TrailMaker());
                Modules.get().add(new TrouserBuild());
                Modules.get().add(new UUIDBan());
                Modules.get().add(new VoiderPlus());
                Commands.add(new CasterTimer());
                Commands.add(new GarbageCleanerCommand());
                Commands.add(new LavaTimeCalculator());
                Commands.add(new TextCommand());
                Commands.add(new ViewNbtCommand());
                Commands.add(new WorldInfoCommand());
                Commands.add(new TriangulateEventsCommand());
                Commands.add(new MovementPatchCommand());
                Hud.get().register(ElytraCount.INFO);
                Hud.get().register(HorseInfo.INFO);
                Hud.get().register(Censor.INFO);
        }

        @Override
        public void onRegisterCategories() {
                // Addon categories are registered by Iekei before module initialization.
        }

        public String getPackage() {
                return "com.iekeiclient.addons.trouserstreak";
        }
}