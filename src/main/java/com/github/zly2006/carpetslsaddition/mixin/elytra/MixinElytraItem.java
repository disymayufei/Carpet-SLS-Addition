package com.github.zly2006.carpetslsaddition.mixin.elytra;

import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ElytraItem.class)
public abstract class MixinElytraItem extends Item implements Equipment {
    public MixinElytraItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasRecipeRemainder() {
        return SLSCarpetSettings.elytraCraftable;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        if (SLSCarpetSettings.elytraCraftable) {
            return stack.copyWithCount(1);
        }
        else return ItemStack.EMPTY;
    }
}
