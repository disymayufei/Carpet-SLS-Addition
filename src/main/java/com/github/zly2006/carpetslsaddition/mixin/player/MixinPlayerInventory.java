package com.github.zly2006.carpetslsaddition.mixin.player;

import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.Nameable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerInventory.class)
public abstract class MixinPlayerInventory implements Inventory, Nameable {
    @Redirect(
            method = "insertStack(ILnet/minecraft/item/ItemStack;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;isInCreativeMode()Z"
            )
    )
    private boolean insertStack(PlayerEntity instance) {
        if (SLSCarpetSettings.creativeNoInfinitePickup) {
            return false;
        }
        return instance.isInCreativeMode();
    }
}
