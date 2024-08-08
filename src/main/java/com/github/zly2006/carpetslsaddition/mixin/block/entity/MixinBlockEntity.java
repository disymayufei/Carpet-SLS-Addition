package com.github.zly2006.carpetslsaddition.mixin.block.entity;

import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntity.class)
public class MixinBlockEntity {
    @Inject(
            method = "supports",
            at = @At("HEAD"),
            cancellable = true
    )
    private void redirectSupports(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!SLSCarpetSettings.restoreOldSculkSensor) return;
        cir.setReturnValue(true);
    }
}
