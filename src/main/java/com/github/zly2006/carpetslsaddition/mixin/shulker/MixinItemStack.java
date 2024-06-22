package com.github.zly2006.carpetslsaddition.mixin.shulker;

import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import com.github.zly2006.carpetslsaddition.util.ShulkerBoxItemUtil;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class MixinItemStack {
    @Inject(
            method = "getMaxCount",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getMaxCount(CallbackInfoReturnable<Integer> cir) {
        if (SLSCarpetSettings.emptyShulkerBoxStack && ShulkerBoxItemUtil.isEmptyShulkerBoxItem((ItemStack) (Object) this)) {
            cir.setReturnValue(ShulkerBoxItemUtil.SHULKERBOX_MAX_STACK_AMOUNT);
        }
    }
}
