package com.github.manasmods.tensura_opac.network.client;

import com.github.manasmods.tensura_opac.capability.OpacCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

class ClientAccess {
    static final Minecraft minecraft = Minecraft.getInstance();

    static void updateOpacCapability(int entityId, CompoundTag tag) {
        if (minecraft.level == null) return;
        Entity entity = minecraft.level.getEntity(entityId);
        if (entity instanceof Player player) OpacCapability.getFrom(player).ifPresent(data -> data.deserializeNBT(tag));
    }
}
