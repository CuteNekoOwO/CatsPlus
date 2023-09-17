package cuteneko.catsplus.item;

import cuteneko.catsplus.block.MyBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;


public class MyItems {
    public static final Item CAT_BAG = new CatBagItem(new FabricItemSettings().fireproof().maxCount(1));
    public static final Item TOTEMEOW = new Item(new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON));
    public static final Item CAT_SPIRIT = new CatSpiritItem(new FabricItemSettings().maxCount(1).fireproof().rarity(Rarity.EPIC));
    public static final Item CAT_RESURRECTION_STATION_ITEM = new BlockItem(MyBlocks.CAT_RESURRECTION_STATION, new FabricItemSettings());
    public static final Item FANG_LUO = new FangLuoItem(new FabricItemSettings().maxCount(1).fireproof().rarity(Rarity.EPIC));

    private static void register(Identifier identifier, Item item) {
        Registry.register(Registries.ITEM, identifier, item);
    }

    public static void register() {
        register(new Identifier("catsplus", "cat_bag"), CAT_BAG);
        register(new Identifier("catsplus", "totemeow"), TOTEMEOW);
        register(new Identifier("catsplus", "cat_spirit"), CAT_SPIRIT);
        register(new Identifier("catsplus", "cat_resurrection_station"), CAT_RESURRECTION_STATION_ITEM);
        register(new Identifier("catsplus", "fang_luo"), FANG_LUO);
    }
}
