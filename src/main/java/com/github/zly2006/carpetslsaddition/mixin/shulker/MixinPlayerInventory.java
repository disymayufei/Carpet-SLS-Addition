package com.github.zly2006.carpetslsaddition.mixin.shulker;

import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import com.github.zly2006.carpetslsaddition.util.ShulkerBoxItemUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerInventory.class)
public abstract class MixinPlayerInventory implements Inventory {
    @Redirect(
            method = "canStackAddMore",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isStackable()Z"
            )
    )
    private boolean isStackable(ItemStack instance) {
        if (SLSCarpetSettings.emptyShulkerBoxStack && ShulkerBoxItemUtil.isEmptyShulkerBoxItem(instance)) {
            return true;
        }
        return instance.isStackable();
    }

    @Redirect(
            method = "offer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;getMaxCount()I"
            )
    )
    private int redirectMaxCount(ItemStack stack) {
        if (SLSCarpetSettings.emptyShulkerBoxStack && ShulkerBoxItemUtil.isEmptyShulkerBoxItem(stack)) {
            return ShulkerBoxItemUtil.SHULKERBOX_MAX_STACK_AMOUNT;
        }
        return Inventory.super.getMaxCount(stack);
    }

    @Override
    public int getMaxCount(ItemStack stack) {
        if (SLSCarpetSettings.emptyShulkerBoxStack && ShulkerBoxItemUtil.isEmptyShulkerBoxItem(stack)) {
            return ShulkerBoxItemUtil.SHULKERBOX_MAX_STACK_AMOUNT;
        }
        return Inventory.super.getMaxCount(stack);
    }
}
