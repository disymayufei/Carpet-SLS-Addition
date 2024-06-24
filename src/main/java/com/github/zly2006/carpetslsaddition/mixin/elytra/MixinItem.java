package com.github.zly2006.carpetslsaddition.mixin.elytra;

import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("ConstantValue")
@Mixin(Item.class)
public abstract class MixinItem {
    @Inject(
            method = "hasRecipeRemainder",
            at = @At("HEAD"),
            cancellable = true
    )
    public void hasRecipeRemainder(CallbackInfoReturnable<Boolean> cir) {
        if (SLSCarpetSettings.elytraCraftable && (Object) this == Items.ELYTRA) {
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "getRecipeRemainder",
            at = @At("HEAD"),
            cancellable = true
    )
    public void getRecipeRemainder(CallbackInfoReturnable<Item> cir) {
        if (SLSCarpetSettings.elytraCraftable && (Object) this == Items.ELYTRA) {
            cir.setReturnValue(Items.ELYTRA);
        }
    }
}
