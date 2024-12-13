package com.github.manasmods.tensura_opac.capability;

import com.github.manasmods.tensura.handler.CapabilityHandler;
import com.github.manasmods.tensura_opac.TensuraOpac;
import com.github.manasmods.tensura_opac.network.OpacNetwork;
import com.github.manasmods.tensura_opac.network.client.SyncOpacCapabilityPacket;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = TensuraOpac.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
@Log4j2
public class OpacCapability implements IOpacCapability {
    public static final Capability<IOpacCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    private static final ResourceLocation ID = new ResourceLocation(TensuraOpac.MOD_ID, "opac_cap");

    @SubscribeEvent
    public static void attach(final AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof Player) {
            e.addCapability(ID, new OpacCapabilityProvider());
        }
    }

    public static LazyOptional<IOpacCapability> getFrom(Player player) {
        return player.getCapability(CAPABILITY);
    }

    public static void sync(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            getFrom(serverPlayer).ifPresent(
                    data -> OpacNetwork.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer), new SyncOpacCapabilityPacket(data, serverPlayer.getId())));
        }
    }

    @Getter
    @Setter
    private int bonusClaimChunk, bonusForceChunk;

    public OpacCapability() {
        this.bonusClaimChunk = 0;
        this.bonusForceChunk = 0;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("bonusClaimChunk", this.bonusClaimChunk);
        tag.putInt("bonusForceChunk", this.bonusForceChunk);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.bonusClaimChunk = tag.getInt("bonusClaimChunk");
        this.bonusForceChunk = tag.getInt("bonusForceChunk");
    }

    public static int getBonusClaimChunk(Player player) {
        IOpacCapability capability = CapabilityHandler.getCapability(player, OpacCapability.CAPABILITY);
        if (capability == null) return 0;
        return capability.getBonusClaimChunk();
    }

    public static void setBonusClaimChunk(Player player, int value) {
        getFrom(player).ifPresent(cap -> {
            cap.setBonusClaimChunk(value);
            OpacCapability.sync(player);
        });
    }

    public static int getBonusForceChunk(Player player) {
        IOpacCapability capability = CapabilityHandler.getCapability(player, OpacCapability.CAPABILITY);
        if (capability == null) return 0;
        return capability.getBonusForceChunk();
    }

    public static void setBonusForceChunk(Player player, int value) {
        getFrom(player).ifPresent(cap -> {
            cap.setBonusForceChunk(value);
            OpacCapability.sync(player);
        });
    }
}