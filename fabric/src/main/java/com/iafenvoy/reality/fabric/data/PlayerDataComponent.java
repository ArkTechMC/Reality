package com.iafenvoy.reality.fabric.data;

import com.iafenvoy.reality.data.PlayerData;
import com.iafenvoy.reality.impl.PlayerDataManager;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class PlayerDataComponent implements ComponentV3, AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<PlayerDataComponent> PLAYER_DATA_COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(PlayerDataManager.DATA_ID, PlayerDataComponent.class);

    private final PlayerData data = new PlayerData();
    private final PlayerEntity player;

    public PlayerDataComponent(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

    public PlayerData getData() {
        return this.data;
    }

    @Override
    public void tick() {
        this.data.tick(this.player);
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.data.deserialize(tag);
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.copyFrom(this.data.serialize());
    }
}
