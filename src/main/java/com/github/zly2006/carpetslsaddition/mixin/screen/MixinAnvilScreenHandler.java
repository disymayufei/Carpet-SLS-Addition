package com.github.zly2006.carpetslsaddition.mixin.screen;

import com.github.zly2006.carpetslsaddition.SLSCarpetSettings;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreenHandler.class)
public class MixinAnvilScreenHandler {
    @Redirect(
            method = "updateResult",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;getAbilities()Lnet/minecraft/entity/player/PlayerAbilities;"
            )
    )
    private PlayerAbilities updateResult(PlayerEntity instance) {
        if (SLSCarpetSettings.creativeObeyEnchantmentRule) {
            var ability = new PlayerAbilities();
            ability.creativeMode = false;
            return ability;
        }

        return instance.getAbilities();
    }
}
