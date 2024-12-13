package com.github.manasmods.tensura_opac.network;

import com.github.manasmods.tensura_opac.TensuraOpac;
import com.github.manasmods.tensura_opac.network.client.SyncOpacCapabilityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;

public class OpacNetwork {
    private static final String PROTOCOL_VERSION = ModList.get().getModFileById(TensuraOpac.MOD_ID).versionString().replaceAll("\\.", "");
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TensuraOpac.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int i = 0;
        INSTANCE.registerMessage(++i, SyncOpacCapabilityPacket.class, SyncOpacCapabilityPacket::toBytes, SyncOpacCapabilityPacket::new, SyncOpacCapabilityPacket::handle);
    }

    public static <T> void toServer(T message) {
        INSTANCE.sendToServer(message);
    }

    public static <T> void toAll(T message) {
        for (ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
            INSTANCE.sendTo(message, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }
}
