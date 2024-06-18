package com.iafenvoy.reality.fabric;

import com.iafenvoy.reality.Reality;
import net.fabricmc.api.ModInitializer;

public class RealityFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Reality.init();
    }
}