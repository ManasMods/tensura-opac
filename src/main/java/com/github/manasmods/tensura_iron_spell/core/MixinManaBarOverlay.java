package com.github.manasmods.tensura_iron_spell.core;

import com.github.manasmods.tensura_iron_spell.TensuraIronSpellConfig;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(io.redspace.ironsspellbooks.gui.overlays.ManaBarOverlay.class)
public class MixinManaBarOverlay {
    @Inject(at = @At(value = "RETURN"), method = "shouldShowManaBar", cancellable = true, remap = false)
    private static void shouldShowManaBar(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (TensuraIronSpellConfig.INSTANCE.manaConversionMultiplier.get() >= 1) cir.setReturnValue(false);
    }
}
