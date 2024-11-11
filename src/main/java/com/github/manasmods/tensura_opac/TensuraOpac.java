package com.github.manasmods.tensura_opac;

import lombok.Getter;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TensuraOpac.MOD_ID)
public class TensuraOpac {
    public static final String MOD_ID = "tensura_opac";
    @Getter
    private static final Logger LOGGER = LogManager.getLogger();

    public TensuraOpac() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::generateData);
    }

    private void generateData(final GatherDataEvent event) {

    }
}
