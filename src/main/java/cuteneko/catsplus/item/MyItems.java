package cuteneko.catsplus.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class MyItems {
    public static final Item CAT_BAG = MyItems.register(
            new Identifier("catsplus", "cat_bag"),
            new CatBagItem(new FabricItemSettings().fireproof().maxCount(1))
    );
    public static final Item TOTEMEOW = MyItems.register(
            new Identifier("catsplus", "totemeow"),
            new Item(new FabricItemSettings().maxCount(1))
    );

    private static Item register(Identifier identifier, Item item) {
        return Registry.register(Registries.ITEM, identifier, item);
    }
}
