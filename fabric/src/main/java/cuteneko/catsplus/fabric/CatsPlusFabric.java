package cuteneko.catsplus.fabric;

import cuteneko.catsplus.CatsPlus;
import net.fabricmc.api.ModInitializer;

public class CatsPlusFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CatsPlus.init();
    }
}
