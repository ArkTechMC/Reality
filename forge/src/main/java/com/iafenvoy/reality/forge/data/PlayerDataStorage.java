package com.iafenvoy.reality.forge.data;

import com.iafenvoy.reality.data.PlayerData;
import net.minecraft.nbt.NbtCompound;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public class PlayerDataStorage implements INBTSerializable<NbtCompound> {
    private final PlayerData playerData = new PlayerData();

    public PlayerDataStorage() {
    }

    @Override
    public NbtCompound serializeNBT() {
        return this.playerData.serialize();
    }

    @Override
    public void deserializeNBT(NbtCompound arg) {
        this.playerData.deserialize(arg);
    }

    public PlayerData getPlayerData() {
        return this.playerData;
    }
}
