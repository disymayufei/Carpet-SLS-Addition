package com.github.zly2006.carpetslsaddition.mixin.shulker;

import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import com.github.zly2006.carpetslsaddition.util.ShulkerBoxItemUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayNetworkHandler {
    @Redirect(
            method = "onCreativeInventoryAction",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;getMaxCount()I"
            )
    )
    private int getMaxCount(ItemStack instance) {
        if (SLSCarpetSettings.emptyShulkerBoxStack && ShulkerBoxItemUtil.isEmptyShulkerBoxItem(instance)) {
            return ShulkerBoxItemUtil.SHULKERBOX_MAX_STACK_AMOUNT;
        }
        return instance.getMaxCount();
    }
}
