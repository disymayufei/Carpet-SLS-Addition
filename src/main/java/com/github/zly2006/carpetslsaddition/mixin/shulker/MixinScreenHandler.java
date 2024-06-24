package com.github.zly2006.carpetslsaddition.mixin.shulker;

import com.github.zly2006.carpetslsaddition.util.ShulkerBoxItemUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ScreenHandler.class)
public abstract class MixinScreenHandler {
    @Redirect(
            method = "*",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;areItemsAndComponentsEqual(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z"
            )
    )
    private boolean checkEqual(ItemStack stack, ItemStack otherStack) {
        if (ShulkerBoxItemUtil.shulkerBoxEqual(stack, otherStack)) return true;
        return ItemStack.areItemsAndComponentsEqual(stack, otherStack);
    }

    @Redirect(
            method = "*",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isStackable()Z"
            )
    )
    private boolean isStackable(ItemStack stack) {
        if (ShulkerBoxItemUtil.isEmptyShulkerBoxItem(stack)) return true;
        return stack.isStackable();
    }
}
