package io.github.manasmods.template.fabric;

import io.github.manasmods.template.Template;
import net.fabricmc.api.ModInitializer;

public final class TemplateFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Template.init();
    }
}
