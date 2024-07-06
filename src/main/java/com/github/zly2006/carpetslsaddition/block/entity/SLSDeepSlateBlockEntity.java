package com.github.zly2006.carpetslsaddition.block.entity;

import com.mojang.datafixers.types.Type;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;

import static net.minecraft.nbt.NbtElement.BYTE_TYPE;

/**
 * 没有很好的让深板岩保留BlockEntity的方案，暂时不使用
 */
@Deprecated
public class SLSDeepSlateBlockEntity extends BlockEntity {
    public static BlockEntityType<SLSDeepSlateBlockEntity> BLOCK_ENTITY_TYPE;

    private boolean deepSlateCore = false;

    public SLSDeepSlateBlockEntity(BlockPos pos, BlockState state) {
        super(SLSDeepSlateBlockEntity.BLOCK_ENTITY_TYPE, pos, state);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains("deep_slate_core", BYTE_TYPE)) {
            deepSlateCore = nbt.getBoolean("deep_slate_core");
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putBoolean("deep_slate_core", deepSlateCore);
    }

    public boolean isDeepSlateCore() {
        return deepSlateCore;
    }

    public void setDeepSlateCore(boolean deepSlateCore) {
        this.deepSlateCore = deepSlateCore;
    }

    public static BlockEntityType<SLSDeepSlateBlockEntity> createType(BlockEntityType.Builder<SLSDeepSlateBlockEntity> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, "sls_deepslate");
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, "sls_deepslate", builder.build(type));
    }
}
