package com.github.manasmods.tensura_iron_spell.data.gen;

import com.github.manasmods.tensura.data.TensuraTags;
import com.github.manasmods.tensura.registry.entity.TensuraEntityTypes;
import com.github.manasmods.tensura_iron_spell.TensuraIronSpell;
import com.github.manasmods.tensura_iron_spell.data.IronSpellTags;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import io.redspace.ironsspellbooks.util.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.Nullable;

public class IronSpellEntityTypeTagProvider extends EntityTypeTagsProvider {
    public IronSpellEntityTypeTagProvider(DataGenerator generator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }
    public IronSpellEntityTypeTagProvider(GatherDataEvent gatherDataEvent) {
        this(gatherDataEvent.getGenerator(), TensuraIronSpell.MOD_ID, gatherDataEvent.getExistingFileHelper());
    }

    protected void addTags() {
        tag(TensuraTags.EntityTypes.HUMAN).add(EntityRegistry.ARCHEVOKER.get(), EntityRegistry.CRYOMANCER.get(),
                EntityRegistry.MAGEHUNTER_VINDICATOR.get(), EntityRegistry.PRIEST.get(), EntityRegistry.PYROMANCER.get());
        tag(TensuraTags.EntityTypes.DROP_CRYSTAL).add(EntityRegistry.CATACOMBS_ZOMBIE.get(), EntityRegistry.KEEPER.get(),
                EntityRegistry.DEAD_KING.get(), EntityRegistry.CULTIST.get(), EntityRegistry.NECROMANCER.get(),
                EntityRegistry.SUMMONED_SKELETON.get(), EntityRegistry.SUMMONED_VEX.get(), EntityRegistry.SUMMONED_ZOMBIE.get());

        tag(TensuraTags.EntityTypes.HERO_BOSS).add(EntityRegistry.DEAD_KING.get());
        tag(TensuraTags.EntityTypes.FULL_GRAVITY_CONTROL).add(EntityRegistry.DEAD_KING.get());
        tag(TensuraTags.EntityTypes.NO_FEAR).add(EntityRegistry.DEAD_KING.get(), EntityRegistry.KEEPER.get());
        tag(TensuraTags.EntityTypes.NO_POSSESSION).add(EntityRegistry.DEAD_KING.get());
        tag(TensuraTags.EntityTypes.NO_MIND_CONTROL).add(EntityRegistry.DEAD_KING.get());
        tag(TensuraTags.EntityTypes.NO_CHARISMA).add(EntityRegistry.DEAD_KING.get(),
                EntityRegistry.KEEPER.get(), EntityRegistry.NECROMANCER.get(), EntityRegistry.CULTIST.get());

        tag(TensuraTags.EntityTypes.CAN_DIE_IN_LABYRINTH).add(EntityRegistry.SUMMONED_SKELETON.get(), EntityRegistry.SUMMONED_VEX.get(),
                EntityRegistry.SUMMONED_ZOMBIE.get(), EntityRegistry.SUMMONED_FROG.get(), EntityRegistry.SUMMONED_POLAR_BEAR.get());
        tag(TensuraTags.EntityTypes.SPIRITUAL).add(EntityRegistry.SUMMONED_VEX.get(), EntityRegistry.DEAD_KING.get());
        tag(TensuraTags.EntityTypes.UNDEAD).add(EntityRegistry.DEAD_KING.get(), EntityRegistry.KEEPER.get(),
                EntityRegistry.CULTIST.get(), EntityRegistry.NECROMANCER.get());

        tag(TensuraTags.EntityTypes.COLD_SOURCE).add(EntityRegistry.CRYOMANCER.get());
        tag(TensuraTags.EntityTypes.CAN_EVAPORATE).add(EntityRegistry.ICE_BLOCK_PROJECTILE.get());
        tag(TensuraTags.EntityTypes.CAN_DISTINGUISH).add(EntityRegistry.FIREBOLT_PROJECTILE.get(), EntityRegistry.FIRE_BOMB.get());

        tag(ModTags.CANT_USE_PORTAL).add(TensuraEntityTypes.CHARYBDIS.get(), TensuraEntityTypes.AKASH.get(),
                TensuraEntityTypes.IFRIT.get(), TensuraEntityTypes.SYLPHIDE.get(), TensuraEntityTypes.UNDINE.get(),
                TensuraEntityTypes.ORC_LORD.get(), TensuraEntityTypes.ORC_DISASTER.get(), TensuraEntityTypes.HINATA_SAKAGUCHI.get());
        tag(IronSpellTags.EntityTypes.UNAFFECTED_BY_SPELL_BOOST).add(EntityRegistry.DEAD_KING.get(),
                EntityRegistry.NECROMANCER.get(), EntityRegistry.KEEPER.get());
    }
}
