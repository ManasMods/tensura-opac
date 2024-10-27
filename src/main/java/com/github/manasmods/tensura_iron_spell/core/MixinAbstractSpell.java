package com.github.manasmods.tensura_iron_spell.core;

import com.github.manasmods.tensura_iron_spell.TensuraIronSpellConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractSpell.class)
public class MixinAbstractSpell {
    @WrapOperation(at = @At(value = "INVOKE", target = "Lio/redspace/ironsspellbooks/api/spells/AbstractSpell;getManaCost(I)I"),
            method = "canBeCastedBy(ILio/redspace/ironsspellbooks/api/spells/CastSource;Lio/redspace/ironsspellbooks/api/magic/MagicData;Lnet/minecraft/world/entity/player/Player;)Lio/redspace/ironsspellbooks/api/spells/CastResult;", remap = false)
    private int shouldShowManaBar(AbstractSpell instance, int level, Operation<Integer> original) {
        int originalCost = original.call(instance, level);
        double magicule = TensuraIronSpellConfig.INSTANCE.manaConversionMultiplier.get();
        if (magicule <= 0) return originalCost;

        double cost = originalCost * magicule;
        if (magicule >= 1) return 0;
        return (int) (originalCost - cost);
    }
}
