package com.iekeiclient.addons.shama.hud;

import com.iekeiclient.engine.settings.*;
import com.iekeiclient.engine.systems.hud.HudElement;
import com.iekeiclient.engine.systems.hud.HudElementInfo;
import com.iekeiclient.engine.systems.hud.HudRenderer;
import com.iekeiclient.engine.utils.render.color.SettingColor;

import static com.iekeiclient.engine.IekeiClient.mc;

/** Region HUD — shows the .mca region file (32x32 chunks) you're standing in, for stash hunting. */
public class RegionHud extends HudElement {
    public static final HudElementInfo<RegionHud> INFO =
        new HudElementInfo<>(com.iekeiclient.addons.shama.ShamaAddon.HUD_GROUP, "shama-region", "Current region file (r.X.Z).", RegionHud::new);

    private final Setting<SettingColor> color = settings.getDefaultGroup().add(new ColorSetting.Builder().name("color").defaultValue(new SettingColor(255, 255, 255, 255)).build());

    public RegionHud() { super(INFO); }

    @Override
    public void render(HudRenderer renderer) {
        String text;
        if (mc.player == null) text = "Region: -";
        else {
            int rx = mc.player.getBlockPos().getX() >> 9;
            int rz = mc.player.getBlockPos().getZ() >> 9;
            text = "Region r." + rx + "." + rz;
        }
        setSize(renderer.textWidth(text, true), renderer.textHeight(true));
        renderer.text(text, x, y, color.get(), true);
    }
}
