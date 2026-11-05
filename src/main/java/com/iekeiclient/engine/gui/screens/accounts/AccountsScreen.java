/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package com.iekeiclient.engine.gui.screens.accounts;

import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.WindowScreen;
import com.iekeiclient.engine.gui.widgets.WAccount;
import com.iekeiclient.engine.gui.widgets.containers.WContainer;
import com.iekeiclient.engine.gui.widgets.containers.WHorizontalList;
import com.iekeiclient.engine.gui.widgets.pressable.WButton;
import com.iekeiclient.engine.systems.accounts.Account;
import com.iekeiclient.engine.systems.accounts.Accounts;
import com.iekeiclient.engine.utils.misc.NbtUtils;
import com.iekeiclient.engine.utils.network.IekeiExecutor;
import org.jetbrains.annotations.Nullable;

import static com.iekeiclient.engine.IekeiClient.mc;

public class AccountsScreen extends WindowScreen {
    public AccountsScreen(GuiTheme theme) {
        super(theme, "Accounts");
    }

    @Override
    public void initWidgets() {
        // Accounts
        for (Account<?> account : Accounts.get()) {
            WAccount wAccount = add(theme.account(this, account)).expandX().widget();
            wAccount.refreshScreenAction = this::reload;
        }

        // Add account
        WHorizontalList l = add(theme.horizontalList()).expandX().widget();

        addButton(l, "Cracked", () -> mc.setScreen(new AddCrackedAccountScreen(theme, this)));
        addButton(l, "Altening", () -> mc.setScreen(new AddAlteningAccountScreen(theme, this)));
        addButton(l, "Microsoft", () -> mc.setScreen(new AddMicrosoftAccountScreen(theme, this)));
    }

    private void addButton(WContainer c, String text, Runnable action) {
        WButton button = c.add(theme.button(text)).expandX().widget();
        button.action = action;
    }

    public static void addAccount(@Nullable AddAccountScreen screen, AccountsScreen parent, Account<?> account) {
        if (screen != null) screen.locked = true;

        IekeiExecutor.execute(() -> {
            if (account.fetchInfo()) {
                account.getCache().loadHead();

                Accounts.get().add(account);
                if (account.login()) Accounts.get().save();

                if (screen != null) {
                    screen.locked = false;
                    screen.close();
                }

                parent.reload();

                return;
            }

            if (screen != null) screen.locked = false;
        });
    }

    @Override
    public boolean toClipboard() {
        return NbtUtils.toClipboard(Accounts.get());
    }

    @Override
    public boolean fromClipboard() {
        return NbtUtils.fromClipboard(Accounts.get());
    }
}
