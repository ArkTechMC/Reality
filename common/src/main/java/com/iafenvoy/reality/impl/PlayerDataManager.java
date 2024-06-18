package com.iafenvoy.reality.impl;

import com.iafenvoy.reality.Reality;
import com.iafenvoy.reality.data.PlayerData;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

public class PlayerDataManager {
    public static final Identifier DATA_ID = new Identifier(Reality.MOD_ID, "player_data");

    @Nullable
    @ExpectPlatform
    public static PlayerData getData(PlayerEntity player) {
        throw new NotImplementedException("This method should be replaced by Architectury.");
    }
}
