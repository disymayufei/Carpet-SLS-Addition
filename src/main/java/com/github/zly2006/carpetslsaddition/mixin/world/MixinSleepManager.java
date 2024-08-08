package com.github.zly2006.carpetslsaddition.mixin.world;

import carpet.patches.EntityPlayerMPFake;
import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.SleepManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SleepManager.class)
public class MixinSleepManager {
    @Redirect(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;isSpectator()Z"))
    private boolean isNormalPlayer(ServerPlayerEntity serverPlayerEntity) {
        if (SLSCarpetSettings.fakePlayersNotOccupiedSleepQuota) {
            return serverPlayerEntity.isSpectator() || serverPlayerEntity instanceof EntityPlayerMPFake;
        }
        else {
            return serverPlayerEntity.isSpectator();
        }
    }
}
