package com.github.zly2006.carpetslsaddition.mixin.network;

import io.netty.channel.ChannelHandlerContext;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(
            method = "exceptionCaught",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;debug(Ljava/lang/String;Ljava/lang/Throwable;)V",
                    ordinal = 1
            ),
            cancellable = true
    )
    /*
     * 阻止调试模式下由于连接超时引发的Minecraft超时断开连接
     * 这只应该被用作调试
     */
    private void avoidTimeout(ChannelHandlerContext context, Throwable ex, CallbackInfo ci) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            ci.cancel();
        }
    }
}
