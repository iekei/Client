package com.iekeiclient.addons.rejects.mixin.iekei;

import com.iekeiclient.addons.rejects.utils.RejectsConfig;
import com.iekeiclient.engine.utils.network.Http;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.net.http.HttpRequest;

@Mixin(value = Http.Request.class, remap = false)
public class HttpRequestMixin {
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/net/http/HttpRequest$Builder;header(Ljava/lang/String;Ljava/lang/String;)Ljava/net/http/HttpRequest$Builder;"))
    private HttpRequest.Builder onAddUAHeader(HttpRequest.Builder builder, String userAgent, String value) {
        if (RejectsConfig.get().httpUserAgent.isBlank()) return builder;
        return builder.header("User-Agent", value);
    }
}
