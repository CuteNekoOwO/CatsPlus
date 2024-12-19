package cuteneko.catsplus.fabric.data.gen;

import cuteneko.catsplus.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static <T> String getHasName(TagKey<T> tag) {
        return "has_tag_" + tag.location().getNamespace() + "_" + tag.location().getPath();
    }

    @Override
    public void buildRecipes(RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CAT_BAG.get())
                .pattern("LLL")
                .pattern("LGL")
                .pattern("LLL")
                .define('L', Items.LEATHER)
                .define('G', Items.GLASS_PANE)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TOTEMEOW.get())
                .pattern("FFF")
                .pattern("FTF")
                .pattern("FFF")
                .define('F', ItemTags.FISHES)
                .define('T', Items.TOTEM_OF_UNDYING)
                .unlockedBy(getHasName(ItemTags.FISHES), has(ItemTags.FISHES))
                .unlockedBy(getHasName(Items.TOTEM_OF_UNDYING), has(Items.TOTEM_OF_UNDYING))
                .save(output);
    }
}
