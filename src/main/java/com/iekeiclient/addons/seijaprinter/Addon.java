/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter;


import com.iekeiclient.addons.seijaprinter.print_main.InitClass;
import com.iekeiclient.addons.AddonCategories;
import com.iekeiclient.addons.seijaprinter.print_main.printer.placedata_getter.vanilla_precision_placer.BlockStateVerify;
import com.iekeiclient.engine.IekeiClient;
import com.iekeiclient.engine.addons.IekeiAddon;
import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Modules;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL;

import java.lang.reflect.Constructor;

import static org.lwjgl.opengl.GL11C.GL_DONT_CARE;
import static org.lwjgl.opengl.GL43.glDebugMessageControl;

public class Addon extends IekeiAddon {
	public static final Logger LOG = LogManager.getLogger();
	public static final Category CATEGORY = AddonCategories.SEIJA_PRINTER;

	@Override
	public void onInitialize() {


        LOG.info("Initializing Seija litematica printer");
        GL.createCapabilities();

        // 禁用OpenGL调试消息
        glDebugMessageControl(GL_DONT_CARE, GL_DONT_CARE, GL_DONT_CARE, (int[]) null, false);
        IekeiClient.EVENT_BUS.subscribe(BlockStateVerify.class);
		// Modules
//        DiskClassLoader cl = new DiskClassLoader();
//        cl.downloadClass();
//        try {
//            LOG.info("Try Load Class1");
//            Class aClass = cl.loadClass("com.iekeiclient.addons.seijaprinter.print_main.InitClass");
//            LOG.info("Try Load Class2");
//
//            Constructor<?> constructor = aClass.getConstructor();
//            LOG.info("Try Load Class3");
//            Object o = constructor.newInstance();
//        } catch (Exception e) {
//            LOG.error(e.getMessage());
//            throw new RuntimeException(e);
//        }
        new InitClass();
	}

    @Override
    public String getPackage() {
        return "com.iekeiclient.addons.seijaprinter";
    }

	@Override
	public void onRegisterCategories() {
		// Addon categories are registered by Iekei before module initialization.
	}
}
