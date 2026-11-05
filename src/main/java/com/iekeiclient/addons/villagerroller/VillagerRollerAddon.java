package com.iekeiclient.addons.villagerroller;

import com.iekeiclient.addons.villagerroller.modules.VillagerRoller;
import com.iekeiclient.addons.AddonCategories;
import com.iekeiclient.engine.addons.IekeiAddon;
import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Modules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VillagerRollerAddon extends IekeiAddon {
    public static final Logger LOG = LogManager.getLogger();
    public static final Category CATEGORY = AddonCategories.VILLAGER_ROLLER;

    @Override
    public void onInitialize() {
        LOG.info("Initializing Iekei Villager Roller");

        // Modules
        Modules.get().add(new VillagerRoller());
    }

    @Override
    public void onRegisterCategories() {
        // Categories are registered by Iekei before addon initialization.
    }

    @Override
    public String getPackage() {
        return "com.iekeiclient.addons.villagerroller";
    }

}
