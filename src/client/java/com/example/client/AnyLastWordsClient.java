package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class AnyLastWordsClient implements ClientModInitializer {
    private boolean wasDead = false;

    @Override
    public void onInitializeClient() {
        ModConfig.load();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            if (!ModConfig.get().enabled) return;

            boolean isDead = client.player.getHealth() <= 0;

            if (isDead && !wasDead) {
                ModConfig config = ModConfig.get();

                String coords;
                if (config.preciseCoords) {
                    coords = "X: " + String.format("%.2f", client.player.getX()) +
                             ", Y: " + String.format("%.2f", client.player.getY()) +
                             ", Z: " + String.format("%.2f", client.player.getZ());
                } else {
                    coords = "X: " + (int) client.player.getX() +
                             ", Y: " + (int) client.player.getY() +
                             ", Z: " + (int) client.player.getZ();
                }

                String message = "§4☠ §cYou died at " + coords;

                if (config.showDimension) {
                    message += " §7[" + getDimensionName(client) + "]";
                }

                client.player.sendSystemMessage(Component.literal(message));
                wasDead = true;
            }

            if (!isDead) wasDead = false;
        });
    }

    private String getDimensionName(Minecraft client) {
        ResourceKey<Level> dimension = client.player.level().dimension();
        if (dimension == Level.OVERWORLD) return "Overworld";
        if (dimension == Level.NETHER) return "The Nether";
        if (dimension == Level.END) return "The End";
        return "Unknown";
    }
}
