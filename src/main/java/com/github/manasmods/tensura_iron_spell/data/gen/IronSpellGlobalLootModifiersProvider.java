package com.github.manasmods.tensura_iron_spell.data.gen;

import com.github.manasmods.tensura.loot.AddItemModifier;
import com.github.manasmods.tensura.registry.items.TensuraMobDropItems;
import com.github.manasmods.tensura_iron_spell.TensuraIronSpell;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.data.event.GatherDataEvent;

public class IronSpellGlobalLootModifiersProvider extends GlobalLootModifierProvider {

    public IronSpellGlobalLootModifiersProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent.getGenerator(), TensuraIronSpell.MOD_ID);
    }

    protected void start() {
        add("entities/dead_king_essence", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation(IronsSpellbooks.MODID, "entities/dead_king")).build()
        }, TensuraMobDropItems.DEMON_ESSENCE.get(), 100F, 1, 1));
    }
}
