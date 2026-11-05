package com.iekeiclient.addons.rejects.utils.accounts;

import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.screens.accounts.AccountsScreen;
import com.iekeiclient.engine.gui.screens.accounts.AddAccountScreen;
import com.iekeiclient.engine.gui.widgets.containers.WTable;
import com.iekeiclient.engine.gui.widgets.input.WTextBox;
import com.iekeiclient.engine.systems.accounts.Accounts;

public class AddCustomYggdrasilAccountScreen extends AddAccountScreen {
    public AddCustomYggdrasilAccountScreen(GuiTheme theme, AccountsScreen parent) {
        super(theme, "Add Yggdrasil Account", parent);
    }

    @Override
    public void initWidgets() {
        WTable t = add(theme.table()).widget();

        // Email
        t.add(theme.label("Username / Email: "));
        WTextBox username = t.add(theme.textBox("")).minWidth(400).expandX().widget();
        username.setFocused(true);
        t.row();

        // Password
        t.add(theme.label("Password: "));
        WTextBox password = t.add(theme.textBox("")).minWidth(400).expandX().widget();
        t.row();

        // Password
        t.add(theme.label("Server: "));
        WTextBox server = t.add(theme.textBox("")).minWidth(400).expandX().widget();
        t.row();

        // Add
        add = t.add(theme.button("Add")).expandX().widget();
        add.action = () -> {
            CustomYggdrasilAccount account = new CustomYggdrasilAccount(username.get(), password.get(), server.get());
            if (!username.get().isEmpty() && !password.get().isEmpty() && !Accounts.get().exists(account)) {
                AccountsScreen.addAccount(this, parent, account);
            }
        };

        enterAction = add.action;
    }
}