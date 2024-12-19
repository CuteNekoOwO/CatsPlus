package cuteneko.catsplus.fabric.client;

import cuteneko.catsplus.client.CatsPlusClient;
import cuteneko.catsplus.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.core.component.DataComponents;

public class CatsPlusFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CatsPlusClient.initClient();

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (tintIndex == 0) {
                var color = stack.get(DataComponents.DYED_COLOR);
                if (color != null) {
                    return color.rgb();
                }
            }
            return -1;
        }, ModItems.CAT_BAG.get());
    }
}
