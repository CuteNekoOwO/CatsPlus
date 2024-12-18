package cuteneko.catsplus.block;

import cuteneko.catsplus.CatsPlus;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(CatsPlus.MODID, Registries.BLOCK);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(CatsPlus.MODID, Registries.ITEM);

    public static void register() {
        BLOCKS.register();
        BLOCK_ITEMS.register();
    }

    public static final RegistrySupplier<Block> CAT_RESURRECTION_STATION_BLOCK = BLOCKS.register("cat_resurrection_station", () -> new CatResurrectionStationBlock(BlockBehaviour.Properties.of().destroyTime(5f)));
    public static final RegistrySupplier<Item> CAT_RESURRECTION_STATION = BLOCK_ITEMS.register("cat_resurrection_station", () -> new BlockItem(CAT_RESURRECTION_STATION_BLOCK.get(), new Item.Properties()));
}
