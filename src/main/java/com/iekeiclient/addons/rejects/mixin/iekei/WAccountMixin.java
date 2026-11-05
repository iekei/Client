package com.iekeiclient.addons.rejects.mixin.iekei;

import com.iekeiclient.addons.rejects.utils.accounts.CustomYggdrasilAccount;
import com.iekeiclient.engine.gui.widgets.WAccount;
import com.iekeiclient.engine.systems.accounts.Account;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = WAccount.class, remap = false)
public class WAccountMixin {
    @Shadow @Final private Account<?> account;

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lcom/iekeiclient/engine/gui/GuiTheme;label(Ljava/lang/String;)Lcom/iekeiclient/engine/gui/widgets/WLabel;",ordinal = 1))
    private String accountName(String text) {
        if (account instanceof CustomYggdrasilAccount) return "(Yggdrasil)";
        return text;
    }
}
