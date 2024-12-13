package com.github.manasmods.tensura_opac.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IOpacCapability extends INBTSerializable<CompoundTag> {
    void setBonusClaimChunk(int i);
    int getBonusClaimChunk();

    void setBonusForceChunk(int i);
    int getBonusForceChunk();
}
