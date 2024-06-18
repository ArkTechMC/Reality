package com.iafenvoy.reality.config;

public class RealityConfig {
    private static RealityConfig INSTANCE = null;

    public boolean enableTemperature = true;
    public boolean enableWater = true;
    public boolean enableWeight = true;
    public double maxTemp = 50;
    public double minTemp = -50;
    public double maxWater = 20;

    public static RealityConfig getInstance() {
        if (INSTANCE == null)
            INSTANCE = ConfigLoader.load(RealityConfig.class, "./config/reality/reality.json", new RealityConfig());
        return INSTANCE;
    }
}
