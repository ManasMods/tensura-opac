package com.github.manasmods.template;

import com.github.manasmods.template.proxy.ClientProxy;
import com.github.manasmods.template.proxy.CommonProxy;
import com.github.manasmods.template.proxy.ServerProxy;
import lombok.Getter;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Template.MOD_ID)
public class Template {
    public static final String MOD_ID = "template"; //TODO replace template with your mod id
    @Getter
    private static final Logger logger = LogManager.getLogger();
    @Getter
    private static Template instance;
    @Getter
    private final CommonProxy proxy;

    public Template() {
        instance = this;
        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        proxy.preInit(modEventBus);
        modEventBus.addListener(proxy::init);
        modEventBus.addListener(proxy::clientSetup);
        modEventBus.addListener(proxy::serverSetup);
        modEventBus.addListener(this::generateData);
    }

    private void generateData(final GatherDataEvent e) {

    }
}
