package cuteneko.catsplus.fabric;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.DyeableItem;

public class CatsPlusFabric implements ModInitializer, ClientModInitializer {
    private final CatsPlus mod;

    public CatsPlusFabric() {
        mod = new CatsPlus();
    }

    @Override
    public void onInitialize() {
        mod.init();
    }

    @Override
    public void onInitializeClient() {
        mod.initClient();

        ColorProviderRegistry.ITEM.register(
                (stack, tintIndex) -> tintIndex == 0 ? ((DyeableItem) stack.getItem()).getColor(stack) : -1,
                ModItems.CAT_BAG.get()
        );
    }
}
