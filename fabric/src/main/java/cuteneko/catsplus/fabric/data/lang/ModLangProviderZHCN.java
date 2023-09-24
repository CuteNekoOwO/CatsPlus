package cuteneko.catsplus.fabric.data.lang;

import cuteneko.catsplus.block.ModBlocks;
import cuteneko.catsplus.effect.ModEffects;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.utility.Constants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModLangProviderZHCN extends FabricLanguageProvider {
    public ModLangProviderZHCN(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add(ModItems.CAT_BAG, "猫包");
        builder.add(ModItems.TOTEMEOW, "不死猫腾");
        builder.add(ModItems.CAT_SPIRIT, "猫魂");
        builder.add(ModItems.FANG_LUO, "坊洛纸模");
        builder.add(Constants.MESSAGE_FANG_LUO_DESCRIPTION, "Don't be sad, have a hug...");

        builder.add(ModBlocks.CAT_RESURRECTION_STATION, "猫咪复活台");

        builder.add(ModEffects.CATTIFY, "猫咪化");
        builder.add(Constants.MESSAGE_CATTIFY_POTION, "变猫药水");
        builder.add(Constants.MESSAGE_CATTIFY_SPLASH_POTION, "喷溅型变猫药水");
        builder.add(Constants.MESSAGE_CATTIFY_LINGERING_POTION, "滞留型变猫药水");
        builder.add(Constants.MESSAGE_CATTIFY_POTION_ARROW, "变猫药水箭");

        builder.add(Constants.MESSAGE_CATS_GROUP_TITLE, "Cats+!");
        builder.add(Constants.MESSAGE_CAT_BAG_NO_CAT, "空的。");
        builder.add(Constants.MESSAGE_CAT_BAG_HAS_CAT, "内含猫猫！");
        builder.add(Constants.MESSAGE_CAT_BAG_HAS_CAT_NAMED, "%1$s 在里面！");
    }
}
