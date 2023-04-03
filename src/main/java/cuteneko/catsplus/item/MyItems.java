package cuteneko.catsplus.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class MyItems {
    public static final CatBagItem CAT_BAG = Registry.register(
            Registries.ITEM,
            new Identifier("catsplus", "cat_bag"),
            new CatBagItem(new FabricItemSettings().fireproof().maxCount(1))
    );
    public static final Item TOTEMEOW = Registry.register(
            Registries.ITEM,
            new Identifier("catsplus", "totemeow"),
            new Item(new FabricItemSettings().maxCount(1))
    );
}
