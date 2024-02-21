package cuteneko.catsplus.fabric;

import cuteneko.catsplus.CatsPlus;
import net.fabricmc.api.ModInitializer;

public class CatsPlusFabric implements ModInitializer {
    private final CatsPlus mod;

    public CatsPlusFabric() {
        mod = new CatsPlus();
    }

    @Override
    public void onInitialize() {
        mod.init();
    }
}
