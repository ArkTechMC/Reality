package com.iafenvoy.reality.data;

import com.iafenvoy.reality.config.RealityConfig;
import com.iafenvoy.reality.config.WeightConfig;
import com.iafenvoy.reality.util.ISerializable;
import com.iafenvoy.reality.util.ITickable;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

import java.util.UUID;

public class WeightData implements ISerializable, ITickable {
    //7107DE5E-7CE8-4030-940E-514C1F160890
    private static final UUID SLOW_UUID = UUID.fromString("923f133c-fc61-4784-b2e9-543c94e99111");
    public boolean enabled = RealityConfig.getInstance().enableWeight;
    public double weight = 0;

    public void measure(PlayerEntity player) {
        this.weight = 0;
        if (player.isCreative() && !WeightConfig.getInstance().affectCreative) return;
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < 41; i++) {
            ItemStack stack = inventory.getStack(i);//TODO: Shulker Box
            if (stack.isEmpty()) continue;
            Object2DoubleMap<Item> map = WeightConfig.getAmountInstance().data;
            this.weight += map.getOrDefault(stack.getItem(), WeightConfig.getInstance().defaultWeight) * stack.getCount();
        }
    }

    @Override
    public void tick(PlayerEntity player) {
        this.measure(player);
        EntityAttributeInstance attributeInstance = player.getAttributes().getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (attributeInstance != null) {
            attributeInstance.removeModifier(SLOW_UUID);
            attributeInstance.addPersistentModifier(new EntityAttributeModifier(SLOW_UUID, "TooHeavy", Math.pow(WeightConfig.getInstance().speedBase + 1, -this.weight) - 1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        player.sendMessage(Text.literal("Weight: " + this.weight + " | Speed: " + player.getMovementSpeed()), true);
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
