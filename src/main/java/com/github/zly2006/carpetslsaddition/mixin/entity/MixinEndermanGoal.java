package com.github.zly2006.carpetslsaddition.mixin.entity;

import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndermanEntity.PickUpBlockGoal.class)
public class



MixinEndermanGoal {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    public boolean isHoldable(BlockState blockState, TagKey<Block> tagKey) {
        if (!SLSCarpetSettings.endermanCanPickUpMushroom) {
            return blockState.isIn(tagKey) && !(blockState.getBlock() instanceof MushroomPlantBlock);
        }
        else {
            return blockState.isIn(tagKey);
        }
    }
}
