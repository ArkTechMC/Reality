package com.iafenvoy.reality.forge.impl;

import com.iafenvoy.reality.data.PlayerData;
import com.iafenvoy.reality.forge.data.PlayerDataProvider;
import com.iafenvoy.reality.forge.data.PlayerDataStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PlayerDataManagerImpl {
    @Nullable
    public static PlayerData getData(PlayerEntity player) {
        LazyOptional<PlayerDataStorage> storageLazyOptional = player.getCapability(PlayerDataProvider.CAPABILITY);
        if (!storageLazyOptional.isPresent()) return null;
        Optional<PlayerDataStorage> storage = storageLazyOptional.resolve();
        return storage.map(PlayerDataStorage::getPlayerData).orElse(null);
    }
}
