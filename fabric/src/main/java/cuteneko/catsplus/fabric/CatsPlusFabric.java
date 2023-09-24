package cuteneko.catsplus.fabric;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.fabric.platform.CatsPlusFabricPlatform;
import cuteneko.catsplus.fabric.registry.PotionRegistry;
import cuteneko.catsplus.fabric.registry.BlockRegistry;
import cuteneko.catsplus.fabric.registry.EffectRegistry;
import cuteneko.catsplus.fabric.registry.ItemGroupRegistry;
import cuteneko.catsplus.fabric.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;

public class CatsPlusFabric implements ModInitializer {
    public CatsPlusFabric() {
        new CatsPlus().setPlatform(new CatsPlusFabricPlatform());
    }

    @Override
    public void onInitialize() {
        ItemRegistry.register();
        ItemGroupRegistry.register();
        EffectRegistry.register();
        PotionRegistry.register();
        BlockRegistry.register();
    }
}
