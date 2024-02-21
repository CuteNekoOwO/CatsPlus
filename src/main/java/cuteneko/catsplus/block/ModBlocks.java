package cuteneko.catsplus.block;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.item.group.ModItemGroups;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(CatsPlus.MODID, RegistryKeys.BLOCK);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(CatsPlus.MODID, RegistryKeys.ITEM);

    public static void register() {
        BLOCKS.register();
        BLOCK_ITEMS.register();
    }

    public static final RegistrySupplier<Block> CAT_RESURRECTION_STATION_BLOCK = BLOCKS.register("cat_resurrection_station", () -> new CatResurrectionStationBlock(AbstractBlock.Settings.create().hardness(5f)));
    public static final RegistrySupplier<Item> CAT_RESURRECTION_STATION = BLOCK_ITEMS.register("cat_resurrection_station", () -> new BlockItem(CAT_RESURRECTION_STATION_BLOCK.get(), new Item.Settings().arch$tab(ModItemGroups.CATS_PLUS)));
}
