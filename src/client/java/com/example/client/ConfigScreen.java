package com.example.client;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {
    private final Screen parent;
    private final ModConfig config = ModConfig.get();

    public ConfigScreen(Screen parent) {
        super(Component.literal("Any Last Words - Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = this.height / 4;

        // Enable/disable toggle
        addRenderableWidget(CycleButton.onOffBuilder(config.enabled)
            .create(centerX - 100, startY, 200, 20,
                Component.literal("Mod Enabled"),
                (button, value) -> {
                    config.enabled = value;
                    ModConfig.save();
                }));

        // Show dimension toggle
        addRenderableWidget(CycleButton.onOffBuilder(config.showDimension)
            .create(centerX - 100, startY + 30, 200, 20,
                Component.literal("Show Dimension"),
                (button, value) -> {
                    config.showDimension = value;
                    ModConfig.save();
                }));

        // Precise coords toggle
        addRenderableWidget(CycleButton.onOffBuilder(config.preciseCoords)
            .create(centerX - 100, startY + 60, 200, 20,
                Component.literal("Precise Coordinates"),
                (button, value) -> {
                    config.preciseCoords = value;
                    ModConfig.save();
                }));

        // Done button
        addRenderableWidget(Button.builder(
            Component.literal("Done"),
            button -> this.minecraft.setScreen(parent))
            .bounds(centerX - 100, startY + 110, 200, 20)
            .build());
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parent);
    }
}
