package com.github.zly2006.carpetslsaddition.mixin.entity;

import carpet.utils.Translations;
import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import com.github.zly2006.carpetslsaddition.block.entity.SLSDeepSlateBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

import static net.minecraft.nbt.NbtElement.COMPOUND_TYPE;

@Mixin(FallingBlockEntity.class)
public abstract class MixinFallingBlockEntity extends Entity {

    @Shadow private BlockState block;
    @Shadow private boolean destroyedOnLanding;

    public MixinFallingBlockEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "handleFallDamage", at = @At(value = "TAIL"))
    private void handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (!SLSCarpetSettings.obtainableReinforcedDeepSlate || !this.block.isIn(BlockTags.ANVIL)) {
            return;
        }

        Predicate<Entity> deepSlatePredicated = (entity) -> {
            if (entity instanceof ItemEntity itemEntity) {
                var itemStack = itemEntity.getStack();
                return itemStack.isOf(Items.DEEPSLATE) && itemStack.getCount() >= 64;
            }

            return false;
        };

        this.getWorld().getOtherEntities(this, this.getBoundingBox(), deepSlatePredicated).forEach((entity) -> {
            var deepSlateEntity = (ItemEntity) entity;  // deepSlatePredicated保证转换的安性
            var stackNbtComponent = deepSlateEntity.getStack().getOrDefault(DataComponentTypes.BLOCK_ENTITY_DATA, NbtComponent.DEFAULT);
            if (!tryCreateDeepSlateCore(deepSlateEntity, stackNbtComponent)) {
                ((ItemEntity) entity).setStack(new ItemStack(Items.REINFORCED_DEEPSLATE, 1));
            }

            markSuccessfulForged();
        });
    }

    /**
     * 尝试创造一个深板岩核心
     * @param itemEntity 深板岩物品实体，不应传入非深板岩的物品
     * @param itemComponent 深板岩物品的Block Entity数据
     * @return 是否创建成功
     */
    @Unique
    private boolean tryCreateDeepSlateCore(ItemEntity itemEntity, NbtComponent itemComponent) {
        AtomicBoolean success = new AtomicBoolean(true);
        var itemStack = itemEntity.getStack().copy();

        itemComponent.apply( blockEntityData -> {
            if (blockEntityData.getBoolean("deep_slate_core")) {
                success.set(false);
                return;
            }

            blockEntityData.putBoolean("deep_slate_core", true);
            BlockItem.setBlockEntityData(itemStack, SLSDeepSlateBlockEntity.BLOCK_ENTITY_TYPE, blockEntityData);

            itemStack.set(
                    DataComponentTypes.CUSTOM_NAME,
                    Text.literal(Translations.tr("carpet.slsa.block.deep_slate_core"))
                            .setStyle(Style.EMPTY.withColor(Formatting.LIGHT_PURPLE))
            );

            itemStack.setCount(1);

            var pos = itemEntity.getPos();
            itemEntity.discard();

            var coreEntity = new ItemEntity(this.getWorld(), pos.getX(), pos.getY(), pos.getZ(), itemStack);
            coreEntity.setPickupDelay(40);
            float f = this.random.nextFloat() * 0.5F;
            float g = this.random.nextFloat() * (float) (Math.PI * 2);
            coreEntity.setVelocity(-MathHelper.sin(g) * f, 0.2F, MathHelper.cos(g) * f);
            this.getWorld().spawnEntity(coreEntity);
        });

        return success.get();
    }

    @Unique
    private void markSuccessfulForged() {
        this.getWorld().playSoundAtBlockCenter(this.getBlockPos(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        this.destroyedOnLanding = true;
    }
}
