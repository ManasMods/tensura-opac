package com.github.manasmods.tensura_iron_spell.core;

import com.github.manasmods.tensura.ability.skill.resist.AbnormalConditionNullification;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.world.effect.MobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(AbnormalConditionNullification.class)
public class MixinAbnormalConditionNullification {
    @Inject(at = @At(value = "RETURN"), method = "getAbnormalEffects", cancellable = true, remap = false)
    private static void isTensuraMagic(CallbackInfoReturnable<List<MobEffect>> cir) {
        List<MobEffect> list = new ArrayList<>();
        if (cir.getReturnValue() != null) list.addAll(cir.getReturnValue());

        list.add(MobEffectRegistry.BLIGHT.get());
        list.add(MobEffectRegistry.SLOWED.get());
        list.add(MobEffectRegistry.REND.get());
        cir.setReturnValue(list);
    }
}
