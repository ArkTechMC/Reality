package com.iafenvoy.reality.fabric.impl;

import com.iafenvoy.reality.data.PlayerData;
import com.iafenvoy.reality.fabric.data.PlayerDataComponent;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PlayerDataManagerImpl {
    @Nullable
    public static PlayerData getData(PlayerEntity player) {
        Optional<PlayerDataComponent> component = PlayerDataComponent.PLAYER_DATA_COMPONENT.maybeGet(player);
        return component.map(PlayerDataComponent::getData).orElse(null);
    }
}
