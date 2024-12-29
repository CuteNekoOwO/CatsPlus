package cuteneko.catsplus.fabric.data.gen.lang;

import cuteneko.catsplus.block.ModBlocks;
import cuteneko.catsplus.effect.ModEffects;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.item.group.ModItemGroups;
import cuteneko.catsplus.utility.ModConstants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModLangProviderENUS extends FabricLanguageProvider {

    public ModLangProviderENUS(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(dataOutput, "en_us", registries);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder builder) {
        builder.add(ModItems.CAT_BAG.get(), "Cat Bag");
        builder.add(ModItems.TOTEMEOW.get(), "Totemeow");
        builder.add(ModItems.CAT_SPIRIT.get(), "Cat Spirit");
        builder.add(ModItems.FANG_LUO.get(), "Doll of Fang_Luo");
        builder.add(ModConstants.MESSAGE_FANG_LUO_DESCRIPTION_1, "In memory of Fang_Luo, a girl who passed away because of cancer.");
        builder.add(ModConstants.MESSAGE_FANG_LUO_DESCRIPTION_2, "Rest in peace, my friend.");

        builder.add(ModBlocks.CAT_RESURRECTION_STATION_BLOCK.get(), "Cat Resurrection Station");

        builder.add(ModEffects.CATTIFY.get(), "Cattify");
        builder.add(ModConstants.MESSAGE_CATTIFY_POTION, "Potion of Cattify");
        builder.add(ModConstants.MESSAGE_CATTIFY_SPLASH_POTION, "Splash Potion of Cattify");
        builder.add(ModConstants.MESSAGE_CATTIFY_LINGERING_POTION, "Lingering Potion of Cattify");
        builder.add(ModConstants.MESSAGE_CATTIFY_POTION_ARROW, "Arrow of Cattify");

        builder.add(ModItemGroups.CATS_PLUS, "Cats+!");
        builder.add(ModConstants.MESSAGE_CAT_BAG_DESCRIPTION_NO_CAT, "Empty.");
        builder.add(ModConstants.MESSAGE_CAT_BAG_DESCRIPTION_HAS_CAT, "Cat Inside!");
        builder.add(ModConstants.MESSAGE_CAT_BAG_DESCRIPTION_HAS_NAMED_CAT, "%1$s Inside!");

        builder.add(ModConstants.MESSAGE_CAT_SPIRIT_NAME, "Spirit of %1$s");
        builder.add(ModConstants.MESSAGE_CAT_SPIRIT_DESCRIPTION_MESSAGE, "");
        builder.add(ModConstants.MESSAGE_CAT_SPIRIT_DESCRIPTION_TIME_LABEL, "Dead at: ");
        builder.add(ModConstants.MESSAGE_CAT_SPIRIT_DESCRIPTION_TIME_PATTERN, "%2$s/%3$s/%1$s %4$s:%5$s:%6$s");
        builder.add(ModConstants.MESSAGE_CAT_SPIRIT_DESCRIPTION_TIME_LONG_TIME_AGO, "Long time ago");

        builder.add(ModConstants.MESSAGE_CAT_DIED, "Your cat was died, its spirit back to its loved owner.");
    }
}
