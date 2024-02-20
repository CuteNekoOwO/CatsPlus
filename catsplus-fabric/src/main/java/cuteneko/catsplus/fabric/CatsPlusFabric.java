package cuteneko.catsplus.fabric;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.fabric.platform.CatsPlusFabricPlatform;
import net.fabricmc.api.ModInitializer;

public class CatsPlusFabric implements ModInitializer {
    private final CatsPlus mod;

    public CatsPlusFabric() {
        mod = new CatsPlus();
        mod.setPlatform(new CatsPlusFabricPlatform());
    }

    @Override
    public void onInitialize() {
        mod.init();
    }
}
