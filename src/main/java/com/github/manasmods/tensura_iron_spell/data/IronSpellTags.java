package com.github.manasmods.tensura_iron_spell.data;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class IronSpellTags {
    public static class EntityTypes {
        public static TagKey<EntityType<?>> UNAFFECTED_BY_SPELL_BOOST = forgeTag("unaffected_by_spell_boost");

        static TagKey<EntityType<?>> forgeTag(String name) {
            return create(new ResourceLocation("forge", name));
        }
        static TagKey<EntityType<?>> create(final ResourceLocation name) {
            return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, name);
        }
    }
}
