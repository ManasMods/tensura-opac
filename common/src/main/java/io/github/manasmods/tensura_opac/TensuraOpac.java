package io.github.manasmods.tensura_opac;

import io.github.manasmods.manascore.config.ConfigRegistry;

public final class TensuraOpac {
    public static final String MOD_ID = "tensura_opac";

    public static void init() {
        ConfigRegistry.registerConfig(new OpacConfig());
        OpacHandler.init();
    }
}
