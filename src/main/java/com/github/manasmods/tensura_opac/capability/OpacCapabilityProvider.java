package com.github.manasmods.tensura_opac.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OpacCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
    private final IOpacCapability defaultCapability = new OpacCapability();
    private final LazyOptional<IOpacCapability> cap = LazyOptional.of(() -> defaultCapability);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == OpacCapability.CAPABILITY) return this.cap.cast();
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return defaultCapability.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        defaultCapability.deserializeNBT(nbt);
    }
}
