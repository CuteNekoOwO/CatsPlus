package cuteneko.catsplus.fabric.registry;

import cuteneko.catsplus.block.ModBlocks;
import cuteneko.catsplus.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistry {
    private static void register(Identifier identifier, Item item) {
        Registry.register(Registries.ITEM, identifier, item);
    }

    public static void register() {
        register(ModItems.ID_CAT_BAG, ModItems.CAT_BAG);
        register(ModItems.ID_TOTEMEOW, ModItems.TOTEMEOW);
        register(ModItems.ID_CAT_SPIRIT, ModItems.CAT_SPIRIT);
        register(ModItems.ID_FANG_LUO, ModItems.FANG_LUO);

        register(ModBlocks.ID_CAT_RESURRECTION_STATION, ModItems.CAT_RESURRECTION_STATION_BLOCK);
    }
}
