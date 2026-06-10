public class AnyLastWords implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.player.isDead()) {
                double x = client.player.getX();
                double y = client.player.getY();
                double z = client.player.getZ();
                client.player.sendMessage(
                    Text.literal("§cYou died at X: " + (int)x + ", Y: " + (int)y + ", Z: " + (int)z),
                    false
                );
            }
        });
    }
}
