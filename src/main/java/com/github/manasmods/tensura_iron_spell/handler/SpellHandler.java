package com.github.manasmods.tensura_iron_spell.handler;

import com.github.manasmods.tensura.ability.SkillHelper;
import com.github.manasmods.tensura_iron_spell.TensuraIronSpell;
import com.github.manasmods.tensura_iron_spell.TensuraIronSpellConfig;
import com.github.manasmods.tensura_iron_spell.data.IronSpellTags;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TensuraIronSpell.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpellHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onSpellCast(final SpellOnCastEvent event) {
        double cost = event.getManaCost();
        if (cost <= 0) return;

        double magicule = TensuraIronSpellConfig.INSTANCE.manaConversionMultiplier.get();
        if (magicule <= 0) return;
        cost *= magicule;

        if (SkillHelper.outOfMagicule(event.getEntity(), cost)) return;
        if (magicule >= 1) event.setManaCost(0);
        else event.setManaCost((int) (event.getManaCost() - cost));
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onSpellDamage(final SpellDamageEvent event) {
        if (event.getEntity().getType().is(IronSpellTags.EntityTypes.UNAFFECTED_BY_SPELL_BOOST)) return;
        double boost = TensuraIronSpellConfig.INSTANCE.spellDamageMultiplier.get();
        if (boost <= 0 || boost == 1) return;
        event.setAmount((float) (event.getAmount() * boost));
    }
}
