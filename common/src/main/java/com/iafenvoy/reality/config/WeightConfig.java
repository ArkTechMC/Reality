package com.iafenvoy.reality.config;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class WeightConfig {
    private static WeightConfig INSTANCE = null;
    private static WeightAmountConfig AMOUNT_INSTANCE = null;
    public double speedMultiplier = 1e-4;
    public boolean affectCreative = true;
    public double defaultWeight = 1;

    public static WeightConfig getInstance() {
        if (INSTANCE == null)
            INSTANCE = ConfigLoader.load(WeightConfig.class, "./config/reality/weight.json", new WeightConfig());
        return INSTANCE;
    }

    public static WeightAmountConfig getAmountInstance() {
        if (AMOUNT_INSTANCE == null)
            AMOUNT_INSTANCE = new WeightAmountConfig();
        return AMOUNT_INSTANCE;
    }

    public static class WeightAmountConfig {
        public final HashMap<Item, Double> data = new HashMap<>();

        public WeightAmountConfig() {
            Map<String, Double> map = ConfigLoader.loadDoubleMap("./config/reality/weight_amount.json");
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                Item item = Registries.ITEM.get(new Identifier(entry.getKey()));
                if (item != Items.AIR)
                    this.data.put(item, entry.getValue());
            }
        }
    }
}
