package com.github.manasmods.tensura_opac.data;

import com.github.manasmods.manascore.api.data.gen.LanguageProvider;
import com.github.manasmods.tensura_opac.TensuraOpac;
import net.minecraftforge.data.event.GatherDataEvent;

public class OpacLanguageProvider extends LanguageProvider {
    public OpacLanguageProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent, TensuraOpac.MOD_ID);
    }
    protected void generate() {
        add("opac.bonus_claim.get", "%s's Bonus Claim Chunk is currently %s.");
        add("opac.bonus_claim.set", "%s's Bonus Claim Chunk has been set to %s.");
        add("opac.bonus_force.get", "%s's Bonus Forceload Chunk is currently %s.");
        add("opac.bonus_force.set", "%s's Bonus Forceload Chunk has been set to %s.");
    }
}
