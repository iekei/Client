package com.iekeiclient.addons.rejects.mixin.iekei;

import com.iekeiclient.addons.rejects.utils.RejectsConfig;
import com.iekeiclient.engine.utils.network.Http;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;


@Mixin(Http.class)
public class HttpMixin {
    
    @ModifyArg(method="get", at= @At(value = "INVOKE", target = "Lcom/iekeiclient/engine/utils/network/Http$Request;<init>(Lcom/iekeiclient/engine/utils/network/Http$Method;Ljava/lang/String;)V"), remap = false)
    private static String onGet(String url) {
        if (RejectsConfig.get().httpAllowed == RejectsConfig.HttpAllowed.Nothing) return "http://0.0.0.0";
        else if (RejectsConfig.get().httpAllowed == RejectsConfig.HttpAllowed.NotIekeiApi && url.startsWith("https://meteorclient.com/api")) return "http://0.0.0.0";
        else if (RejectsConfig.get().httpAllowed == RejectsConfig.HttpAllowed.NotIekeiPing && url.startsWith("https://meteorclient.com/api/online")) return "http://0.0.0.0";
        return url;
    }

    @ModifyArg(method="post", at= @At(value = "INVOKE", target = "Lcom/iekeiclient/engine/utils/network/Http$Request;<init>(Lcom/iekeiclient/engine/utils/network/Http$Method;Ljava/lang/String;)V"), remap = false)
    private static String onPost(String url) {
        if (RejectsConfig.get().httpAllowed == RejectsConfig.HttpAllowed.Nothing) return "http://0.0.0.0";
        else if (RejectsConfig.get().httpAllowed == RejectsConfig.HttpAllowed.NotIekeiApi && url.startsWith("https://meteorclient.com/api")) return "http://0.0.0.0";
        else if (RejectsConfig.get().httpAllowed == RejectsConfig.HttpAllowed.NotIekeiPing && url.startsWith("https://meteorclient.com/api/online")) return "http://0.0.0.0";
        return url;
    }
}
