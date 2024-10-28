package com.github.manasmods.tensura_iron_spell.core;

import com.github.manasmods.manascore.api.skills.ManasSkillInstance;
import com.github.manasmods.tensura.ability.skill.resist.ThermalFluctuationNullification;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ThermalFluctuationNullification.class)
public class MixinThermalFluctationNullification {
    @Inject(at = @At(value = "RETURN"), method = "getImmuneEffects", cancellable = true, remap = false)
    private void isTensuraMagic(ManasSkillInstance instance, LivingEntity entity, CallbackInfoReturnable<List<MobEffect>> cir) {
        if (!instance.isToggled()) return;
        List<MobEffect> list = new ArrayList<>();
        if (cir.getReturnValue() != null) list.addAll(cir.getReturnValue());
        list.add(MobEffectRegistry.CHILLED.get());
        cir.setReturnValue(list);
    }
}
