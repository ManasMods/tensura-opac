package io.github.manasmods.tensura_opac.fabric;

import io.github.manasmods.tensura_opac.TensuraOpac;
import net.fabricmc.api.ModInitializer;

public final class TensuraOpacFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TensuraOpac.init();
    }
}
