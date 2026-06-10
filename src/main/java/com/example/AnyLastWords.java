public class AnyLastWords implements ModInitializer {
    @Override
    public void onInitialize() {
        ClientPlayNetworking.registerGlobalReceiver(...);
    }
}
