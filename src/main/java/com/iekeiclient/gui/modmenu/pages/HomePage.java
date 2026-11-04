package com.iekeiclient.gui.modmenu.pages;

import com.iekeiclient.gui.api.IekeiGui;
import com.iekeiclient.gui.api.page.Page;
import com.iekeiclient.gui.api.page.impl.RightLeftTransition;
import com.iekeiclient.skia.font.Icon;

public class HomePage extends Page {

	public HomePage(IekeiGui parent) {
		super(parent, "text.home", Icon.HOME, new RightLeftTransition(true));
	}

}
