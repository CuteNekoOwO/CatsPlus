package cuteneko.catsplus.item;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Identifier ID_CAT_BAG = new Identifier(CatsPlus.MODID, "cat_bag");
    public static final Identifier ID_TOTEMEOW = new Identifier(CatsPlus.MODID, "totemeow");
    public static final Identifier ID_CAT_SPIRIT = new Identifier(CatsPlus.MODID, "cat_spirit");
    public static final Identifier ID_FANG_LUO = new Identifier(CatsPlus.MODID, "fang_luo");

    public static final Item CAT_BAG = new CatBagItem(new Item.Settings().fireproof().maxCount(1));
    public static final Item TOTEMEOW = new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON));
    public static final Item CAT_SPIRIT = new CatSpiritItem(new Item.Settings().maxCount(1).fireproof().rarity(Rarity.EPIC));
    public static final Item FANG_LUO = new FangLuoItem(new Item.Settings().maxCount(1).fireproof().rarity(Rarity.EPIC));

    public static final Item CAT_RESURRECTION_STATION_BLOCK = new BlockItem(ModBlocks.CAT_RESURRECTION_STATION, new Item.Settings());
}
