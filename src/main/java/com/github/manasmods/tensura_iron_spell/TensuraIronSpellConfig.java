package com.github.manasmods.tensura_iron_spell;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class TensuraIronSpellConfig {
    public static final TensuraIronSpellConfig INSTANCE;
    public static final ForgeConfigSpec SPEC;

    static {
        Pair<TensuraIronSpellConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(TensuraIronSpellConfig::new);
        INSTANCE = pair.getKey();
        SPEC = pair.getValue();
    }

    public final ForgeConfigSpec.DoubleValue manaConversionMultiplier;

    private TensuraIronSpellConfig(ForgeConfigSpec.Builder builder) {
        builder.push("manaConversionMultiplier");
        this.manaConversionMultiplier = builder.comment("The multiplier for how much Mana costed to cast spells would be converted to Magicule cost in Tensura")
                .defineInRange("manaConversionMultiplier", 5, 0D, 1_000_000_000D);
        builder.pop();
    }
}
