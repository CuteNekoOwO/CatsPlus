package cuteneko.catsplus.block;

import cuteneko.catsplus.CatsPlus;
import games.moegirl.sinocraft.sinocore.registry.IRegRef;
import games.moegirl.sinocraft.sinocore.registry.IRegistry;
import games.moegirl.sinocraft.sinocore.registry.RegistryManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final IRegistry<Block> BLOCKS = RegistryManager.obtain(CatsPlus.MODID, Registries.BLOCK);
    public static final IRegistry<Item> BLOCK_ITEMS = RegistryManager.obtain(CatsPlus.MODID, Registries.ITEM);

    public static void register() {
        BLOCKS.register();
        BLOCK_ITEMS.register();
    }

    public static final IRegRef<Block> CAT_RESURRECTION_STATION_BLOCK = BLOCKS.register("cat_resurrection_station", () -> new CatResurrectionStationBlock(BlockBehaviour.Properties.of().destroyTime(5f)));
    public static final IRegRef<Item> CAT_RESURRECTION_STATION = BLOCK_ITEMS.register("cat_resurrection_station", () -> new BlockItem(CAT_RESURRECTION_STATION_BLOCK.get(), new Item.Properties()));
}
