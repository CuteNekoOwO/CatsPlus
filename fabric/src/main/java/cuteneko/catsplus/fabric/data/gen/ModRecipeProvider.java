package cuteneko.catsplus.fabric.data.gen;

import cuteneko.catsplus.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    private static <T> String hasTag(TagKey<T> tag) {
        return "has_tag_" + tag.id().getNamespace() + "_" + tag.id().getPath();
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CAT_BAG.get())
                .pattern("LLL")
                .pattern("LGL")
                .pattern("LLL")
                .input('L', Items.LEATHER)
                .input('G', Items.GLASS_PANE)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.GLASS_PANE), conditionsFromItem(Items.GLASS_PANE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOTEMEOW.get())
                .pattern("FFF")
                .pattern("FTF")
                .pattern("FFF")
                .input('F', ItemTags.FISHES)
                .input('T', Items.TOTEM_OF_UNDYING)
                .criterion(hasTag(ItemTags.FISHES), conditionsFromTag(ItemTags.FISHES))
                .criterion(hasItem(Items.TOTEM_OF_UNDYING), conditionsFromItem(Items.TOTEM_OF_UNDYING))
                .offerTo(exporter);
    }
}
