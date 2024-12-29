package cuteneko.catsplus.fabric.data.gen;

import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(ModItemTags.COOKED_FISHES)
                .add(Items.COOKED_COD)
                .add(Items.COOKED_SALMON);

        getOrCreateTagBuilder(ModItemTags.CAT_INTERACTABLE)
                .add(ModItems.CAT_BAG.get())
                .add(ModItems.TOTEMEOW.get());
    }
}
