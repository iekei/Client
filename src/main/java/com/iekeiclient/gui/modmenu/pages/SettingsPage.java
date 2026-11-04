package com.iekeiclient.gui.modmenu.pages;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.iekeiclient.Iekei;
import com.iekeiclient.animation.SimpleAnimation;
import com.iekeiclient.gui.api.IekeiGui;
import com.iekeiclient.gui.api.page.Page;
import com.iekeiclient.gui.api.page.impl.LeftRightTransition;
import com.iekeiclient.gui.api.page.impl.RightLeftTransition;
import com.iekeiclient.management.color.api.ColorPalette;
import com.iekeiclient.management.mod.Mod;
import com.iekeiclient.skia.Skia;
import com.iekeiclient.skia.font.Fonts;
import com.iekeiclient.skia.font.Icon;
import com.iekeiclient.utils.SearchUtils;
import com.iekeiclient.utils.language.I18n;
import com.iekeiclient.utils.mouse.MouseUtils;

public class SettingsPage extends Page {

	private List<Item> items = new ArrayList<>();

	public SettingsPage(IekeiGui parent) {
		super(parent, "text.settings", Icon.SETTINGS, new RightLeftTransition(true));

		for (Mod m : Iekei.getInstance().getModManager().getMods()) {
			if (m.isHidden()) {
				items.add(new Item(m));
			}
		}
	}

	@Override
	public void draw(double mouseX, double mouseY) {
		super.draw(mouseX, mouseY);

		ColorPalette palette = Iekei.getInstance().getColorManager().getPalette();

		float offsetY = 96;

		mouseY = mouseY - scrollHelper.getValue();

		Skia.save();
		Skia.translate(0, scrollHelper.getValue());

		for (Item i : items) {

			SimpleAnimation yAnimation = i.yAnimation;
			Mod m = i.mod;

			if (!searchBar.getText().isEmpty() && !SearchUtils
					.isSimilar(I18n.get(m.getName()) + " " + I18n.get(m.getDescription()), searchBar.getText())) {
				continue;
			}

			float itemY = y + offsetY;

			yAnimation.onTick(itemY, 14);

			itemY = yAnimation.getValue();

			Skia.drawRoundedRect(x + 32, itemY, width - 64, 68, 18, palette.getSurface());
			Skia.drawFullCenteredText(m.getIcon(), x + 32 + 30, itemY + (68 / 2), palette.getOnSurface(),
					Fonts.getIcon(32));
			Skia.drawText(I18n.get(m.getName()), x + 32 + 52, itemY + 20, palette.getOnSurface(), Fonts.getRegular(17));
			Skia.drawText(I18n.get(m.getDescription()), x + 32 + 52, itemY + 37, palette.getOnSurfaceVariant(),
					Fonts.getRegular(14));
			Skia.drawHeightCenteredText(">", x + width - 54, itemY + (68 / 2), palette.getOnSurface(),
					Fonts.getRegular(20));

			offsetY += 68 + 18;
		}

		scrollHelper.setMaxScroll(offsetY, height);
		Skia.restore();
	}

	@Override
	public void mousePressed(double mouseX, double mouseY, int button) {
		super.mousePressed(mouseX, mouseY, button);

		mouseY = mouseY - scrollHelper.getValue();
		searchBar.mousePressed(mouseX, mouseY, button);
	}

	@Override
	public void mouseReleased(double mouseX, double mouseY, int button) {
		super.mouseReleased(mouseX, mouseY, button);

		mouseY = mouseY - scrollHelper.getValue();

		searchBar.mouseReleased(mouseX, mouseY, button);

		for (Item i : items) {

			float itemY = i.yAnimation.getValue();
			Mod m = i.mod;

			if (!searchBar.getText().isEmpty() && !SearchUtils
					.isSimilar(I18n.get(m.getName()) + " " + I18n.get(m.getDescription()), searchBar.getText())) {
				continue;
			}

			if (MouseUtils.isInside(mouseX, mouseY, x + 32, itemY, width - 64, 68)
					&& button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
				parent.setCurrentPage(new SettingsImplPage(parent, this.getClass(), m));
				this.setTransition(new LeftRightTransition(true));
			}
		}
	}

	@Override
	public void onClosed() {
		this.setTransition(new RightLeftTransition(true));
	}

	private class Item {

		private SimpleAnimation yAnimation = new SimpleAnimation();
		private Mod mod;

		private Item(Mod mod) {
			this.mod = mod;
		}
	}
}
