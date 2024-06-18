package com.iafenvoy.reality.data;

import com.iafenvoy.reality.util.ISerializable;
import com.iafenvoy.reality.util.ITickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class PlayerData implements ISerializable, ITickable {
    public final TemperatureData temperature = new TemperatureData();
    public final WaterData water = new WaterData();
    public final WeightData weight = new WeightData();

    @Override
    public void tick(PlayerEntity player) {
        this.temperature.tick(player);
        this.water.tick(player);
        this.weight.tick(player);
    }

    @Override
    public NbtCompound serialize() {
        NbtCompound compound = new NbtCompound();
        compound.put("temperature", this.temperature.serialize());
        compound.put("water", this.water.serialize());
        compound.put("weight", this.weight.serialize());
        return compound;
    }

    @Override
    public void deserialize(NbtCompound compound) {
        this.temperature.deserialize(compound.getCompound("temperature"));
        this.water.deserialize(compound.getCompound("water"));
        this.weight.deserialize(compound.getCompound("weight"));
    }
}
