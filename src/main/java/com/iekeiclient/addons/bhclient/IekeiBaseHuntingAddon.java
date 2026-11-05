package com.iekeiclient.addons.bhclient;

import com.mojang.logging.LogUtils;
import com.iekeiclient.addons.bhclient.commands.BaseHuntCommand;
import com.iekeiclient.addons.bhclient.hud.BaseHuntingHud;
import com.iekeiclient.addons.bhclient.modules.AutoLog;
import com.iekeiclient.addons.bhclient.modules.IekeiBaseHunter;
import com.iekeiclient.addons.bhclient.modules.IekeiBaseHunterV2;
import com.iekeiclient.addons.bhclient.modules.NewChunks;
import com.iekeiclient.addons.bhclient.modules.PlayerLogger;
import com.iekeiclient.addons.bhclient.modules.StashFinder;
import com.iekeiclient.addons.bhclient.modules.WebhookNotifier;
import com.iekeiclient.addons.bhclient.systems.PlayerTracker;
import com.iekeiclient.addons.bhclient.systems.StashManager;
import com.iekeiclient.addons.bhclient.utils.WebhookManager;
import com.iekeiclient.engine.addons.GithubRepo;
import com.iekeiclient.engine.addons.IekeiAddon;
import com.iekeiclient.engine.commands.Commands;
import com.iekeiclient.engine.systems.Systems;
import com.iekeiclient.engine.systems.hud.Hud;
import com.iekeiclient.engine.systems.hud.HudGroup;
import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Modules;
import org.slf4j.Logger;

public class IekeiBaseHuntingAddon extends IekeiAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Base Hunting");
    public static final HudGroup HUD_GROUP = new HudGroup("Base Hunting");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Base Hunting Client");

        // Systems have to be registered before Modules.load() runs, otherwise their saved data is lost.
        Systems.add(new StashManager());
        Systems.add(new PlayerTracker());

        Modules.get().add(new IekeiBaseHunter());
        Modules.get().add(new IekeiBaseHunterV2());
        Modules.get().add(new NewChunks());
        Modules.get().add(new StashFinder());
        Modules.get().add(new WebhookNotifier());
        Modules.get().add(new PlayerLogger());
        Modules.get().add(new AutoLog());

        Commands.add(new BaseHuntCommand());

        Hud.get().register(BaseHuntingHud.INFO);

        WebhookManager.get().start();
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.iekeiclient.addons.bhclient";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("logvex-sg", "basehuntingclient-baritone", "main", null);
    }
}
