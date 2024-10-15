package com.github.zly2006.carpetslsaddition.mixin.entity;

import com.github.zly2006.carpetslsaddition.util.access.PlayerAccessor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity implements PlayerAccessor {
    @Unique
    private MutableText displayName = null;
    @Shadow
    protected abstract MutableText addTellClickEvent(MutableText component);

    @Override
    public void carpet_SLS_Addition$setDisplayName(MutableText name) {
        this.displayName = name;
    }

    @Inject(method = "getDisplayName", at = @At("HEAD"), cancellable = true)
    public void injectGetDisplayName(CallbackInfoReturnable<Text> cir) {
        System.out.println(displayName);
        if (this.displayName != null) {
            cir.setReturnValue(addTellClickEvent(displayName));
        }
    }
}
