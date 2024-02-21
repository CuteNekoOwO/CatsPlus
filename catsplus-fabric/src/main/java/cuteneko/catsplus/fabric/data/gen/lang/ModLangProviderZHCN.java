package cuteneko.catsplus.fabric.data.gen.lang;

import cuteneko.catsplus.block.ModBlocks;
import cuteneko.catsplus.effect.ModEffects;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.item.group.ModItemGroups;
import cuteneko.catsplus.utility.Constants;
import dev.architectury.registry.registries.DeferredSupplier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.ItemGroup;

public class ModLangProviderZHCN extends FabricLanguageProvider {
    public ModLangProviderZHCN(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add(ModItems.CAT_BAG.get(), "猫包");
        builder.add(ModItems.TOTEMEOW.get(), "不死猫腾");
        builder.add(ModItems.CAT_SPIRIT.get(), "猫魂");
        builder.add(ModItems.FANG_LUO.get(), "坊洛玩偶");
        builder.add(Constants.MESSAGE_FANG_LUO_DESCRIPTION_1, "纪念因肿瘤离去的坊洛。");
        builder.add(Constants.MESSAGE_FANG_LUO_DESCRIPTION_2, "缅怀……");

        builder.add(ModBlocks.CAT_RESURRECTION_STATION_BLOCK.get(), "猫咪复活台");

        builder.add(ModEffects.CATTIFY.get(), "猫咪化");
        builder.add(Constants.MESSAGE_CATTIFY_POTION, "变猫药水");
        builder.add(Constants.MESSAGE_CATTIFY_SPLASH_POTION, "喷溅型变猫药水");
        builder.add(Constants.MESSAGE_CATTIFY_LINGERING_POTION, "滞留型变猫药水");
        builder.add(Constants.MESSAGE_CATTIFY_POTION_ARROW, "变猫药水箭");

        builder.add(((DeferredSupplier<ItemGroup>) ModItemGroups.CATS_PLUS).getKey(), "Cats+!");
        builder.add(Constants.MESSAGE_CAT_BAG_DESCRIPTION_NO_CAT, "空的。");
        builder.add(Constants.MESSAGE_CAT_BAG_DESCRIPTION_HAS_CAT, "内含猫猫！");
        builder.add(Constants.MESSAGE_CAT_BAG_DESCRIPTION_HAS_NAMED_CAT, "%1$s 在里面！");

        builder.add(Constants.MESSAGE_CAT_SPIRIT_NAME, "%1$s 的灵魂");
        builder.add(Constants.MESSAGE_CAT_SPIRIT_DESCRIPTION_MESSAGE, "");
        builder.add(Constants.MESSAGE_CAT_SPIRIT_DESCRIPTION_TIME_LABEL, "卒于：");
        builder.add(Constants.MESSAGE_CAT_SPIRIT_DESCRIPTION_TIME_PATTERN, "%1$s年%2$s月%3$s日 %4$s:%5$s:%6$s");
        builder.add(Constants.MESSAGE_CAT_SPIRIT_DESCRIPTION_TIME_LONG_TIME_AGO, "很久以前");
    }
}
