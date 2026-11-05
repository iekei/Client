/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.themes.iekei.widgets;

import com.iekeiclient.engine.gui.WidgetScreen;
import com.iekeiclient.engine.gui.themes.iekei.IekeiWidget;
import com.iekeiclient.engine.gui.widgets.WAccount;
import com.iekeiclient.engine.systems.accounts.Account;
import com.iekeiclient.engine.utils.render.color.Color;

public class WIekeiAccount extends WAccount implements IekeiWidget {
    public WIekeiAccount(WidgetScreen screen, Account<?> account) {
        super(screen, account);
    }

    @Override
    protected Color loggedInColor() {
        return theme().loggedInColor.get();
    }

    @Override
    protected Color accountTypeColor() {
        return theme().textSecondaryColor.get();
    }
}
