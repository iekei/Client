/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.loader;

import com.iekeiclient.addons.seijaprinter.Addon;
import com.iekeiclient.addons.seijaprinter.print_main.printer.util.RenderHelper;
import com.iekeiclient.addons.seijaprinter.print_main.printer.util.RenderUtil;
import com.iekeiclient.engine.events.render.Render3DEvent;
import com.iekeiclient.engine.events.world.TickEvent;
import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;

public abstract class LoaderAntiCrash extends Module {

    public LoaderAntiCrash(Category category, String name, String description) {
        super(category, name, description);
    }

    @EventHandler
    public void onRender3d(Render3DEvent event) {
        render3d(event);
    }
    public void render3d(Render3DEvent event){

    }
//    @EventHandler
//    public void onRender2d(Render2DEvent event){
//        if (aniRenderSize<=30||renderMode.get()!=RenderMode.ANIMATION)return;
//        Vector3d vec3 = new Vector3d(aniRenderCenter.x,aniRenderCenter.y,aniRenderCenter.z);
//        if (NametagUtils.to2D(vec3, 2)) {
//            NametagUtils.begin(vec3);
//            TextRenderer textRenderer = TextRenderer.get();
//            textRenderer.begin(1, false, true);
//
//            String text = mc.player.getMainHandStack().getItem().getName().getString();//String.format("%.1f", renderDamage);
//            double w = textRenderer.getWidth(text) / 2;
//            //RenderUtils.drawItem(event.drawContext, mc.player.getMainHandStack(), (int)textRenderer.getWidth(""), (int)-textRenderer.getHeight() ,3, true);
//
//            textRenderer.render(text, -w, 0, Color.BLACK, true);
//
//            textRenderer.end();
//            NametagUtils.end();
//        }
//
//    }

    @EventHandler
    public void onTick(TickEvent.Post e) {
        tick(e);
    }
    public void tick(TickEvent.Post e) {

    }
}
