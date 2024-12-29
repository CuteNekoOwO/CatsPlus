package cuteneko.catsplus.fabric.client;

import cuteneko.catsplus.client.CatsPlusClient;
import net.fabricmc.api.ClientModInitializer;

public class CatsPlusFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CatsPlusClient.setupClient();
    }
}
