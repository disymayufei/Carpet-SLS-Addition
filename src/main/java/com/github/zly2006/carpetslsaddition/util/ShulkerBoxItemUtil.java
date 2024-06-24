package com.github.zly2006.carpetslsaddition.util;

import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public class ShulkerBoxItemUtil {
    public static final int SHULKERBOX_MAX_STACK_AMOUNT = 64;

    public static boolean shulkerBoxEqual(ItemStack stack, ItemStack otherStack) {
        if (ShulkerBoxItemUtil.isEmptyShulkerBoxItem(stack) && ShulkerBoxItemUtil.isEmptyShulkerBoxItem(otherStack)) {
            if (stack.isEmpty() && otherStack.isEmpty()) {
                return true;
            }
            return Objects.equals(
                    stack.getComponents().filtered(s -> s != DataComponentTypes.CONTAINER),
                    stack.getComponents().filtered(s -> s != DataComponentTypes.CONTAINER)
            );
        }
        return ItemStack.areItemsAndComponentsEqual(stack, otherStack);
    }

    public static boolean isEmptyShulkerBoxItem(ItemStack itemStack) {
        if (itemStack.getItem() instanceof BlockItem &&
                ((BlockItem) itemStack.getItem()).getBlock() instanceof ShulkerBoxBlock) {
            if (itemStack.getComponents().contains(DataComponentTypes.CONTAINER)) {
                return !itemStack.getComponents().get(DataComponentTypes.CONTAINER).iterateNonEmpty().iterator().hasNext();
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

}
