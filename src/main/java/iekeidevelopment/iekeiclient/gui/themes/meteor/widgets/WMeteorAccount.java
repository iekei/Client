/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.gui.themes.meteor.widgets;

import iekeidevelopment.iekeiclient.gui.WidgetScreen;
import iekeidevelopment.iekeiclient.gui.themes.meteor.MeteorWidget;
import iekeidevelopment.iekeiclient.gui.widgets.WAccount;
import iekeidevelopment.iekeiclient.systems.accounts.Account;
import iekeidevelopment.iekeiclient.utils.render.color.Color;

public class WMeteorAccount extends WAccount implements MeteorWidget {
    public WMeteorAccount(WidgetScreen screen, Account<?> account) {
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
