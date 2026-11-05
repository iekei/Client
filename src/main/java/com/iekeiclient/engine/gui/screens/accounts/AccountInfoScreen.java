/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.screens.accounts;

import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.WindowScreen;
import com.iekeiclient.engine.gui.widgets.containers.WHorizontalList;
import com.iekeiclient.engine.gui.widgets.pressable.WButton;
import com.iekeiclient.engine.systems.accounts.Account;
import com.iekeiclient.engine.systems.accounts.TokenAccount;
import com.iekeiclient.engine.utils.render.color.Color;

import static com.iekeiclient.engine.IekeiClient.mc;

public class AccountInfoScreen extends WindowScreen {
    private final Account<?> account;

    public AccountInfoScreen(GuiTheme theme, Account<?> account) {
        super(theme, account.getUsername() + " details");
        this.account = account;
    }

    @Override
    public void initWidgets() {
        TokenAccount e = (TokenAccount) account;
        WHorizontalList l = add(theme.horizontalList()).expandX().widget();

        WButton copy = theme.button("Copy");
        copy.action = () -> mc.keyboard.setClipboard(e.getToken());

        l.add(theme.label("TheAltening token:"));
        l.add(theme.label(e.getToken()).color(Color.GRAY)).pad(5);
        l.add(copy);
    }
}
