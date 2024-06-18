package com.iafenvoy.reality.data;

import com.iafenvoy.reality.config.RealityConfig;
import com.iafenvoy.reality.config.WeightConfig;
import com.iafenvoy.reality.util.ISerializable;
import com.iafenvoy.reality.util.ITickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;

public class WeightData implements ISerializable, ITickable {
    public boolean enabled = RealityConfig.getInstance().enableWeight;
    public double weight = 0;

    public void measure(PlayerEntity player) {
        this.weight = 0;
        if (player.isCreative() && !WeightConfig.getInstance().affectCreative) return;
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < 41; i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isEmpty()) continue;
            HashMap<Item, Double> map = WeightConfig.getAmountInstance().data;
            this.weight += map.getOrDefault(stack.getItem(), WeightConfig.getInstance().defaultWeight) * stack.getCount();
        }
    }

    @Override
    public void tick(PlayerEntity player) {
        this.measure(player);
        System.out.println(player.getMovementSpeed());
    }

    @Override
    public NbtCompound serialize() {
        NbtCompound compound = new NbtCompound();
        compound.putBoolean("enabled", this.enabled);
        compound.putDouble("weight", this.weight);
        return compound;
    }

    @Override
    public void deserialize(NbtCompound compound) {
        this.enabled = compound.getBoolean("enabled");
        this.weight = compound.getDouble("weight");
    }
}
