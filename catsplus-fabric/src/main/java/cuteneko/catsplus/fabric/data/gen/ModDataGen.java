package cuteneko.catsplus.fabric.data.gen;

import cuteneko.catsplus.fabric.data.gen.lang.ModLangProviderENUS;
import cuteneko.catsplus.fabric.data.gen.lang.ModLangProviderZHCN;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        var pack = generator.createPack();
        pack.addProvider(ModLangProviderZHCN::new);
        pack.addProvider(ModLangProviderENUS::new);

        pack.addProvider(ModItemModelProvider::new);

        pack.addProvider(ModRecipeProvider::new);
    }
}
