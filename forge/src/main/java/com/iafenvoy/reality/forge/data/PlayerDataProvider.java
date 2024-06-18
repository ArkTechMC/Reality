package com.iafenvoy.reality.forge.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerDataProvider implements ICapabilitySerializable<NbtCompound> {
    public static final Capability<PlayerDataStorage> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    private PlayerDataStorage storage;
    private final LazyOptional<PlayerDataStorage> storageLazyOptional = LazyOptional.of(this::getOrCreateStorage);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        return CAPABILITY.orEmpty(capability, this.storageLazyOptional);
    }

    @Override
    public NbtCompound serializeNBT() {
        return this.getOrCreateStorage().serializeNBT();
    }

    @Override
    public void deserializeNBT(NbtCompound arg) {
        this.getOrCreateStorage().deserializeNBT(arg);
    }

    private PlayerDataStorage getOrCreateStorage() {
        if (this.storage == null)
            this.storage = new PlayerDataStorage();
        return this.storage;
    }
}
