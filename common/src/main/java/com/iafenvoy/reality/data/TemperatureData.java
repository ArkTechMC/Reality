package com.iafenvoy.reality.data;

import com.iafenvoy.reality.Reality;
import com.iafenvoy.reality.config.RealityConfig;
import com.iafenvoy.reality.util.ISerializable;
import com.iafenvoy.reality.util.ITickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class TemperatureData implements ISerializable, ITickable {
    public boolean enabled = RealityConfig.getInstance().enableTemperature;
    public double maxTemp = RealityConfig.getInstance().maxTemp;
    public double minTemp = RealityConfig.getInstance().minTemp;
    public double temperature = 0;
    public double targetTemp = 0;

    @Override
    public void tick(PlayerEntity player) {

    }

    @Override
    public NbtCompound serialize() {
        NbtCompound compound = new NbtCompound();
        compound.putBoolean("enabled", this.enabled);
        compound.putDouble("maxTemp", this.maxTemp);
        compound.putDouble("minTemp", this.minTemp);
        compound.putDouble("temperature", this.temperature);
        compound.putDouble("targetTemp", this.targetTemp);
        return compound;
    }

    @Override
    public void deserialize(NbtCompound compound) {
        this.enabled = compound.getBoolean("enabled");
        this.maxTemp = compound.getDouble("maxTemp");
        this.minTemp = compound.getDouble("minTemp");
        this.temperature = compound.getDouble("temperature");
        this.targetTemp = compound.getDouble("targetTemp");
    }
}
