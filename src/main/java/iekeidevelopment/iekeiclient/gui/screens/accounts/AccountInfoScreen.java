/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package iekeidevelopment.iekeiclient.gui.screens.accounts;

import iekeidevelopment.iekeiclient.gui.GuiTheme;
import iekeidevelopment.iekeiclient.gui.WindowScreen;
import iekeidevelopment.iekeiclient.gui.widgets.containers.WHorizontalList;
import iekeidevelopment.iekeiclient.gui.widgets.pressable.WButton;
import iekeidevelopment.iekeiclient.systems.accounts.Account;
import iekeidevelopment.iekeiclient.systems.accounts.AccountType;
import iekeidevelopment.iekeiclient.systems.accounts.TokenAccount;
import iekeidevelopment.iekeiclient.utils.render.color.Color;

import static iekeidevelopment.iekeiclient.IekeiClient.mc;

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

        String tokenLabel = account.getType() + " token:";
        if (account.getType() == AccountType.Session) tokenLabel = "";

        WButton copy = theme.button("Copy");
        copy.action = () -> mc.keyboard.setClipboard(e.getToken());

        l.add(theme.label(tokenLabel));
        l.add(theme.label(account.getType() == AccountType.Session ? "Click to copy Token" : e.getToken()).color(Color.GRAY)).pad(5);
        l.add(copy);
    }
}
