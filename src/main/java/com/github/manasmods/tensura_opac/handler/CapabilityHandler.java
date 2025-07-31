package com.github.manasmods.tensura_opac.handler;

import com.github.manasmods.tensura_opac.TensuraOpac;
import com.github.manasmods.tensura_opac.capability.IOpacCapability;
import com.github.manasmods.tensura_opac.capability.OpacCapability;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TensuraOpac.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityHandler {

    public static void registerCapabilities(RegisterCapabilitiesEvent e) {
        e.register(IOpacCapability.class);
    }

    @SubscribeEvent
    static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
        Player player = e.getEntity();
        OpacCapability.sync(player);
    }

    @SubscribeEvent
    static void onPlayerTrack(PlayerEvent.StartTracking e) {
        Entity entity = e.getTarget();
        if (entity instanceof Player player) {
            OpacCapability.sync(player);
            OpacCapability.sync(e.getEntity());
        }
    }

    @SubscribeEvent
    static void onPlayerClone(PlayerEvent.Clone e) {
        e.getOriginal().reviveCaps();
        OpacCapability.getFrom(e.getOriginal()).ifPresent(oldData ->
                OpacCapability.getFrom(e.getEntity()).ifPresent(data -> data.deserializeNBT(oldData.serializeNBT())));
        e.getOriginal().invalidateCaps();
    }

    @SubscribeEvent
    static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent e) {
        OpacCapability.sync(e.getEntity());
    }

    @SubscribeEvent
    static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent e) {
        OpacCapability.sync(e.getEntity());
    }
}