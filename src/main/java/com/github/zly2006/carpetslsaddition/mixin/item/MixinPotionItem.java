package com.github.zly2006.carpetslsaddition.mixin.item;

import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PotionItem.class)
public abstract class MixinPotionItem extends Item {
    public MixinPotionItem(Settings settings) {
        super(settings);
    }

    @Inject(method = "useOnBlock", at = @At(value = "HEAD"), cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = context.getStack();
        PlayerEntity player = context.getPlayer();

        if (!SLSCarpetSettings.useDyeOnShulkerBox ||
                player == null ||
                itemStack.getItem() != Items.POTION ||
                PotionUtil.getPotion(itemStack) != Potions.WATER) {
            return;
        }

        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();

        if (block instanceof ShulkerBoxBlock && ((ShulkerBoxBlock) block).getColor() != null) {
            if (!world.isClient()) {
                ShulkerBoxBlockEntity blockEntity = (ShulkerBoxBlockEntity) world.getBlockEntity(pos);
                BlockState newBlockState = Blocks.SHULKER_BOX.getDefaultState().
                        with(ShulkerBoxBlock.FACING, blockState.get(ShulkerBoxBlock.FACING));

                if (world.setBlockState(pos, newBlockState)) {
                    ShulkerBoxBlockEntity newBlockEntity = (ShulkerBoxBlockEntity) world.getBlockEntity(pos);
                    assert blockEntity != null;
                    assert newBlockEntity != null;
                    newBlockEntity.readInventoryNbt(blockEntity.createNbt());
                    newBlockEntity.setCustomName(blockEntity.getCustomName());
                    newBlockEntity.markDirty();
                    if (!player.isCreative()) {
                        context.getStack().decrement(1);
                        Objects.requireNonNull(context.getPlayer()).getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE));
                    }
                }
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        }
    }
}
