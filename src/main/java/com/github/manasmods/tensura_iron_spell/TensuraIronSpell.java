package com.github.manasmods.tensura_iron_spell;

import com.github.manasmods.tensura_iron_spell.data.IronSpellEntityEPProvider;
import lombok.Getter;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TensuraIronSpell.MOD_ID)
public class TensuraIronSpell {
    public static final String MOD_ID = "tensura_iron_spell";
    public static final String CONFIG_DIR = "tensura-reincarnated";
    @Getter
    private static final Logger LOGGER = LogManager.getLogger();

    public TensuraIronSpell() {
        FileUtils.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve(CONFIG_DIR), CONFIG_DIR);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TensuraIronSpellConfig.SPEC, getConfigFileName("iron_spell_common"));

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::generateData);
    }

    private void generateData(final GatherDataEvent event) {
        event.getGenerator().addProvider(event.includeServer(), new IronSpellEntityEPProvider(event));
    }

    private String getConfigFileName(String name) {
        return String.format("%s/%s.toml", CONFIG_DIR, name);
    }
}
