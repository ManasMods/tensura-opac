package com.github.manasmods.template.proxy;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

public class CommonProxy {
    public void preInit(final IEventBus modEventBus) {

    }

    public void init(final FMLCommonSetupEvent e) {

    }

    public void clientSetup(final FMLClientSetupEvent e) {}

    public void serverSetup(final FMLDedicatedServerSetupEvent e) {}
}
