package com.iekeiclient.management.mod.impl.hud;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.iekeiclient.Iekei;
import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.client.RenderSkiaEvent;
import com.iekeiclient.management.mod.Mod;
import com.iekeiclient.management.mod.api.hud.HUDMod;
import com.iekeiclient.management.mod.settings.impl.BooleanSetting;
import com.iekeiclient.skia.Skia;
import com.iekeiclient.skia.font.Fonts;
import com.iekeiclient.skia.font.Icon;
import com.iekeiclient.utils.language.I18n;

import io.github.humbleui.skija.Font;

public class ActiveModulesMod extends HUDMod {

	private final BooleanSetting includeHudModulesSetting = new BooleanSetting("setting.includehudmodules",
			"setting.includehudmodules.description", Icon.LIST, this, false);

	public ActiveModulesMod() {
		super("mod.activemodules.name", "mod.activemodules.description", Icon.LIST);
	}

	public final EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> draw();

	private void draw() {
		List<String> activeModules = new ArrayList<>();

		for (Mod mod : Iekei.getInstance().getModManager().getMods()) {
			if (mod == this || !mod.isEnabled() || mod.isHidden()
					|| (!includeHudModulesSetting.isEnabled() && mod instanceof HUDMod)) {
				continue;
			}

			activeModules.add(I18n.get(mod.getName()));
		}

		Font font = Fonts.getRegular(9);
		activeModules.sort(Comparator.comparingDouble(
				(String name) -> -Skia.getTextBounds(name, font).getWidth()).thenComparing(Comparator.naturalOrder()));

		float width = 0;
		for (String name : activeModules) {
			width = Math.max(width, Skia.getTextBounds(name, font).getWidth());
		}

		float padding = 5;
		float lineHeight = 15;
		float panelWidth = width + padding * 2;
		float panelHeight = activeModules.size() * lineHeight;
		position.setSize(panelWidth, panelHeight);

		if (activeModules.isEmpty()) {
			return;
		}

		begin();
		for (int i = 0; i < activeModules.size(); i++) {
			String name = activeModules.get(i);
			float textWidth = Skia.getTextBounds(name, font).getWidth();
			float itemX = getX() + panelWidth - textWidth - padding * 2;
			float itemY = getY() + i * lineHeight;

			drawBackground(itemX, itemY, textWidth + padding * 2, lineHeight - 2);
			drawText(name, itemX + padding, itemY + 2, font);
		}
		finish();
	}

	@Override
	public float getRadius() {
		return 5;
	}
}
