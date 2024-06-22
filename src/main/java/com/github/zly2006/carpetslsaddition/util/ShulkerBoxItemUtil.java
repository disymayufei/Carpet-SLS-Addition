package com.github.zly2006.carpetslsaddition.util;

import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

public class ShulkerBoxItemUtil {
    public static final int SHULKERBOX_MAX_STACK_AMOUNT = 64;

    public static boolean shulkerBoxEqual(ItemStack stack, ItemStack otherStack) {
        if (ShulkerBoxItemUtil.isEmptyShulkerBoxItem(stack) && ShulkerBoxItemUtil.isEmptyShulkerBoxItem(otherStack)) {
            if (stack.isEmpty() && otherStack.isEmpty()) {
                return true;
            }
            return Objects.equals(
                    stack.getComponents().filtered(s -> s != DataComponentTypes.BLOCK_ENTITY_DATA),
                    stack.getComponents().filtered(s -> s != DataComponentTypes.BLOCK_ENTITY_DATA)
            );
        }
        return ItemStack.areItemsAndComponentsEqual(stack, otherStack);
    }

    public static boolean isEmptyShulkerBoxItem(ItemStack itemStack) {
        if (itemStack.getItem() instanceof BlockItem &&
                ((BlockItem) itemStack.getItem()).getBlock() instanceof ShulkerBoxBlock) {
            NbtComponent tag = itemStack.getComponents().get(DataComponentTypes.BLOCK_ENTITY_DATA);
            if (tag != null) {
                if (tag.contains("Items")) {
                    NbtList tagList = tag.getNbt().getList("Items", 10);
                    return tagList.size() <= 0;
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
