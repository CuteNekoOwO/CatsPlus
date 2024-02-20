package cuteneko.catsplus.fabric.data.lang;

import cuteneko.catsplus.block.ModBlocks;
import cuteneko.catsplus.effect.ModEffects;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.utility.Constants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModLangProviderENUS extends FabricLanguageProvider {
    public ModLangProviderENUS(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add(ModItems.CAT_BAG, "Cat Bag");
        builder.add(ModItems.TOTEMEOW, "Totemeow");
        builder.add(ModItems.CAT_SPIRIT, "Cat Spirit");
        builder.add(ModItems.FANG_LUO, "Paper Model of Fang_Luo");
        builder.add(Constants.MESSAGE_FANG_LUO_DESCRIPTION, "Don't be sad, have a hug...");     // Don't change to other language when translating.

        builder.add(ModBlocks.CAT_RESURRECTION_STATION, "Cat Resurrection Station");

        builder.add(ModEffects.CATTIFY, "Cattify");
        builder.add(Constants.MESSAGE_CATTIFY_POTION, "Potion of Cattify");
        builder.add(Constants.MESSAGE_CATTIFY_SPLASH_POTION, "Splash Potion of Cattify");
        builder.add(Constants.MESSAGE_CATTIFY_LINGERING_POTION, "Lingering Potion of Cattify");
        builder.add(Constants.MESSAGE_CATTIFY_POTION_ARROW, "Arrow of Cattify");

        builder.add(Constants.MESSAGE_CATS_GROUP_TITLE, "Cats+!");
        builder.add(Constants.MESSAGE_CAT_BAG_NO_CAT, "Empty.");
        builder.add(Constants.MESSAGE_CAT_BAG_HAS_CAT, "Cat Inside!");
        builder.add(Constants.MESSAGE_CAT_BAG_HAS_CAT_NAMED, "%1$s Inside!");
    }
}
