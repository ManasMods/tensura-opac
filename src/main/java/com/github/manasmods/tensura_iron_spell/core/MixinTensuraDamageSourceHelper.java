package com.github.manasmods.tensura_iron_spell.core;

import com.github.manasmods.tensura.util.damage.DamageSourceHelper;
import io.redspace.ironsspellbooks.damage.ISpellDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageSourceHelper.class)
public class MixinTensuraDamageSourceHelper {
    @Inject(at = @At(value = "RETURN"), method = "isTensuraMagic", cancellable = true, remap = false)
    private static void isTensuraMagic(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (damageSource instanceof ISpellDamageSource) cir.setReturnValue(true);
    }
}
