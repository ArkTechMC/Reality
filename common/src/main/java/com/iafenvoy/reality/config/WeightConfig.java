package com.iafenvoy.reality.config;

import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class WeightConfig {
    private static WeightConfig INSTANCE = null;
    private static WeightAmountConfig AMOUNT_INSTANCE = null;
    public double speedBase = 1e-6;
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
        public final Object2DoubleMap<Item> data = new Object2DoubleArrayMap<>();

        public WeightAmountConfig() {
            Object2DoubleMap<String> map = ConfigLoader.loadDoubleMap("./config/reality/weight_amount.json");
            for (Object2DoubleMap.Entry<String> entry : map.object2DoubleEntrySet()) {
                Item item = Registries.ITEM.get(new Identifier(entry.getKey()));
                if (item != Items.AIR)
                    this.data.put(item, entry.getDoubleValue());
            }
        }
    }
}
