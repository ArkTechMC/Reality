package com.iafenvoy.reality.util;

import net.minecraft.nbt.NbtCompound;

public interface ISerializable {
    NbtCompound serialize();

    void deserialize(NbtCompound compound);
}
