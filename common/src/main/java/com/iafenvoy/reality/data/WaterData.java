package com.iafenvoy.reality.data;

import com.iafenvoy.reality.config.RealityConfig;
import com.iafenvoy.reality.util.ISerializable;
import com.iafenvoy.reality.util.ITickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class WaterData implements ISerializable, ITickable {
    public boolean enabled = RealityConfig.getInstance().enableWater;
    public double maxWater = RealityConfig.getInstance().maxWater;
    public double currentWater = 20;

    @Override
    public void tick(PlayerEntity player) {

    }

    @Override
    public NbtCompound serialize() {
        NbtCompound compound = new NbtCompound();
        compound.putBoolean("enabled", this.enabled);
        compound.putDouble("maxWater", this.maxWater);
        compound.putDouble("currentWater", this.currentWater);
        return compound;
    }

    @Override
    public void deserialize(NbtCompound compound) {
        this.enabled = compound.getBoolean("enabled");
        this.maxWater = compound.getDouble("maxWater");
        this.currentWater = compound.getDouble("currentWater");
    }
}
