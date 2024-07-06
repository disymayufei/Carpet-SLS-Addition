package com.github.zly2006.carpetslsaddition.mixin.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Item.class)
public class MixinItem {
    @Redirect(
            method = "hasGlint",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;hasEnchantments()Z"
            )
    )
    public boolean hasGlint(ItemStack itemStack) {
        if (!itemStack.isOf(Items.DEEPSLATE)) {
            return itemStack.hasEnchantments();
        }

        return itemStack.getOrDefault(
                DataComponentTypes.BLOCK_ENTITY_DATA,
                NbtComponent.DEFAULT
        ).copyNbt().getBoolean("deep_slate_core") || itemStack.hasEnchantments();
    }
}
