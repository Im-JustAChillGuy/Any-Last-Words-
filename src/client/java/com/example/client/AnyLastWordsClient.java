package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class AnyLastWordsClient implements ClientModInitializer {
    private boolean wasDead = false;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            boolean isDead = client.player.getHealth() <= 0;

            if (isDead && !wasDead) {
                int x = (int) client.player.getX();
                int y = (int) client.player.getY();
                int z = (int) client.player.getZ();

                client.player.sendSystemMessage(
                    Component.literal("§4☠ §cYou died at X: " + x + ", Y: " + y + ", Z: " + z)
                );
                wasDead = true;
            }

            if (!isDead) {
                wasDead = false;
            }
        });
    }
}
