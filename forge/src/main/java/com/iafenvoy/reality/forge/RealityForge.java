package com.iafenvoy.reality.forge;

import com.iafenvoy.reality.Reality;
import com.iafenvoy.reality.data.PlayerData;
import com.iafenvoy.reality.forge.data.PlayerDataProvider;
import com.iafenvoy.reality.forge.impl.PlayerDataManagerImpl;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

@Mod(Reality.MOD_ID)
@Mod.EventBusSubscriber(modid = Reality.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RealityForge {
    public RealityForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Reality.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Reality.init();
    }

    @SubscribeEvent
    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity player) {
            boolean isServerNotFake = player instanceof ServerPlayerEntity && !(player instanceof FakePlayer);
            if ((isServerNotFake || player instanceof AbstractClientPlayerEntity) && !player.getCapability(PlayerDataProvider.CAPABILITY).isPresent())
                event.addCapability(new Identifier(Reality.MOD_ID, "player_data"), new PlayerDataProvider());
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        MinecraftServer server = event.getServer();
        List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
        for (PlayerEntity player : players) {
            PlayerData data = PlayerDataManagerImpl.getData(player);
            if (data != null) data.tick(player);
        }
    }
}