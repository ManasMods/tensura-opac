package com.github.manasmods.tensura_iron_spell;

import lombok.Getter;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TensuraIronSpell.MOD_ID)
public class TensuraIronSpell {
    public static final String MOD_ID = "tensura_iron_spell";
    @Getter
    private static final Logger LOGGER = LogManager.getLogger();

    public TensuraIronSpell() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    private void generateData(final GatherDataEvent e) {
    }
}
