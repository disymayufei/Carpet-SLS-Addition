package com.github.zly2006.carpetslsaddition.mixin.block;

import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.SculkSensorBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.enums.SculkSensorPhase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SculkSensorBlock.class)
public abstract class MixinSculkSensorBlock extends BlockWithEntity implements Waterloggable {
    @Shadow
    private static void updateNeighbors(World world, BlockPos pos, BlockState state) {}

    protected MixinSculkSensorBlock(Settings settings) {
        super(settings);
    }

    @Inject(
            method = "onStateReplaced",
            at = @At("HEAD"),
            cancellable = true
    )
    public void injectOnRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) {
        if (!SLSCarpetSettings.restoreOldSculkSensor) return;

        if (!state.isOf(newState.getBlock())) {
            if (SculkSensorBlock.getPhase(state) == SculkSensorPhase.ACTIVE) {
                updateNeighbors(world, pos, state);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }

        ci.cancel();
    }
}
