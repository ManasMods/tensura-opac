package com.github.manasmods.tensura_opac.network.client;

import com.github.manasmods.tensura_opac.capability.IOpacCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncOpacCapabilityPacket {
    private final CompoundTag tag;
    private final int entityId;

    public SyncOpacCapabilityPacket(FriendlyByteBuf buf) {
        tag = buf.readAnySizeNbt();
        entityId = buf.readInt();
    }

    public SyncOpacCapabilityPacket(IOpacCapability data, int entityId) {
        tag = data.serializeNBT();
        this.entityId = entityId;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(tag);
        buf.writeInt(entityId);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientAccess.updateOpacCapability(entityId, tag)));
        ctx.get().setPacketHandled(true);
    }
}
