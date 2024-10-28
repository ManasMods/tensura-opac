package com.github.manasmods.tensura_iron_spell.data.gen;

import com.github.manasmods.manascore.api.data.gen.CustomDataProvider;
import com.github.manasmods.tensura.data.pack.EntityEPCount;
import com.github.manasmods.tensura.registry.magic.SpiritualMagics;
import com.github.manasmods.tensura.registry.skill.ExtraSkills;
import com.github.manasmods.tensura.registry.skill.IntrinsicSkills;
import com.github.manasmods.tensura.registry.skill.ResistanceSkills;
import com.google.gson.JsonElement;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class IronSpellEntityEPProvider extends CustomDataProvider {
    public IronSpellEntityEPProvider(GatherDataEvent gatherDataEvent) {
        super("entity/ep", gatherDataEvent.getGenerator());
    }

    public String getName() {
        return "Iron Spell Entity EP";
    }

    protected void run(BiConsumer<ResourceLocation, Supplier<JsonElement>> biConsumer) {
        List<ResourceLocation> apothecaristList = new ArrayList<>();
        apothecaristList.add(ResistanceSkills.POISON_RESISTANCE.getId());
        EntityEPCount.of(EntityType.getKey(EntityRegistry.APOTHECARIST.get()), 10000, 12000, apothecaristList).buildJson(biConsumer);
        EntityEPCount.of(EntityType.getKey(EntityRegistry.ARCHEVOKER.get()), 10000, 12000).buildJson(biConsumer);
        EntityEPCount.of(EntityType.getKey(EntityRegistry.CATACOMBS_ZOMBIE.get()), 1500, 3000).buildJson(biConsumer);

        List<ResourceLocation> keeperList = new ArrayList<>();
        keeperList.add(ExtraSkills.STEEL_STRENGTH.getId());
        EntityEPCount.of(EntityType.getKey(EntityRegistry.KEEPER.get()), 10000, 20000, keeperList).buildJson(biConsumer);

        List<ResourceLocation> cryoList = new ArrayList<>();
        cryoList.add(ResistanceSkills.COLD_RESISTANCE.getId());
        cryoList.add(SpiritualMagics.WATER.getId());
        cryoList.add(SpiritualMagics.WATER_CUTTER.getId());
        EntityEPCount.of(EntityType.getKey(EntityRegistry.CRYOMANCER.get()), 8000, 12000, cryoList).buildJson(biConsumer);

        List<ResourceLocation> cultistList = new ArrayList<>();
        cultistList.add(ResistanceSkills.DARKNESS_ATTACK_RESISTANCE.getId());
        cultistList.add(SpiritualMagics.DARKNESS.getId());
        EntityEPCount.of(EntityType.getKey(EntityRegistry.CULTIST.get()), 10000, 13000, cultistList).buildJson(biConsumer);

        List<ResourceLocation> deadKingList = new ArrayList<>();
        deadKingList.add(SpiritualMagics.DARKNESS.getId());
        deadKingList.add(SpiritualMagics.TRUE_DARKNESS.getId());
        deadKingList.add(ResistanceSkills.POISON_RESISTANCE.getId());
        deadKingList.add(ResistanceSkills.DARKNESS_ATTACK_RESISTANCE.getId());
        deadKingList.add(ResistanceSkills.ABNORMAL_CONDITION_RESISTANCE.getId());
        deadKingList.add(ResistanceSkills.PHYSICAL_ATTACK_RESISTANCE.getId());
        deadKingList.add(ExtraSkills.MAGIC_SENSE.getId());
        deadKingList.add(ExtraSkills.SAGE.getId());
        deadKingList.add(IntrinsicSkills.BLOOD_MIST.getId());
        EntityEPCount.of(EntityType.getKey(EntityRegistry.DEAD_KING.get()), 80000, 160000, deadKingList).buildJson(biConsumer);
        EntityEPCount.of(EntityType.getKey(EntityRegistry.MAGEHUNTER_VINDICATOR.get()), 5000, 6000).buildJson(biConsumer);

        List<ResourceLocation> nercomancerList = new ArrayList<>();
        nercomancerList.add(ResistanceSkills.DARKNESS_ATTACK_RESISTANCE.getId());
        nercomancerList.add(SpiritualMagics.DARKNESS.getId());
        nercomancerList.add(SpiritualMagics.SUMMON_HOUND_DOG.getId());
        EntityEPCount.of(EntityType.getKey(EntityRegistry.NECROMANCER.get()), 10000, 12000, nercomancerList).buildJson(biConsumer);

        List<ResourceLocation> priestList = new ArrayList<>();
        priestList.add(ResistanceSkills.LIGHT_ATTACK_RESISTANCE.getId());
        priestList.add(SpiritualMagics.LIGHT.getId());
        EntityEPCount.of(EntityType.getKey(EntityRegistry.PRIEST.get()), 8000, 12000, priestList).buildJson(biConsumer);

        List<ResourceLocation> pyroList = new ArrayList<>();
        pyroList.add(ResistanceSkills.FLAME_ATTACK_RESISTANCE.getId());
        pyroList.add(SpiritualMagics.FIRE.getId());
        pyroList.add(SpiritualMagics.FIRE_BOLT.getId());
        pyroList.add(SpiritualMagics.FIRE_BREATH.getId());
        EntityEPCount.of(EntityType.getKey(EntityRegistry.PYROMANCER.get()), 8000, 12000, pyroList).buildJson(biConsumer);

        EntityEPCount.of(EntityType.getKey(EntityRegistry.SPECTRAL_STEED.get()), 100, 200).buildJson(biConsumer);
        EntityEPCount.of(EntityType.getKey(EntityRegistry.SUMMONED_FROG.get()), 10, 100).buildJson(biConsumer);
        EntityEPCount.of(EntityType.getKey(EntityRegistry.SUMMONED_POLAR_BEAR.get()), 1450, 2000).buildJson(biConsumer);
        EntityEPCount.of(EntityType.getKey(EntityRegistry.SUMMONED_SKELETON.get()), 2000, 3000).buildJson(biConsumer);
        EntityEPCount.of(EntityType.getKey(EntityRegistry.SUMMONED_VEX.get()), 1900, 3000).buildJson(biConsumer);
        EntityEPCount.of(EntityType.getKey(EntityRegistry.SUMMONED_ZOMBIE.get()), 1400, 2500).buildJson(biConsumer);
    }
}
