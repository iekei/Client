/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.print_main;

import com.iekeiclient.addons.seijaprinter.Addon;
import com.iekeiclient.addons.seijaprinter.print_main.modules.*;
import com.iekeiclient.addons.seijaprinter.settings.PrinterSettings;
import com.iekeiclient.engine.systems.modules.Modules;

import java.util.logging.Logger;

public class InitClass {
    public InitClass() {
//        //Addon.LOG.info("loader");
//        try {
//
        PrinterSettings.getINSTANCE().addSettings();
        Modules.get().add(new PlaceDebug());
        Modules.get().add(new ScheDebug());


        Modules.get().add(Printer.getINSTANCE());
        Modules.get().add(new ItemSearcher());
        Modules.get().add(new RayTraceTest());
//            Modules.get().add(new YanZhen());
        Modules.get().add(new SideTest());
        Modules.get().add(new SwapTest());

//        }catch (Exception e){
//           // Addon.LOG.info(e.getMessage());
//        }
    }
}
