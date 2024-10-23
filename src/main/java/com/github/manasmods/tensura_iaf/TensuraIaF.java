package com.github.manasmods.tensura_iaf;

import lombok.Getter;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TensuraIaF.MOD_ID)
public class TensuraIaF {
    public static final String MOD_ID = "tensura_iaf";
    @Getter
    private static final Logger LOGGER = LogManager.getLogger();

    public TensuraIaF() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    private void generateData(final GatherDataEvent e) {
    }
}
