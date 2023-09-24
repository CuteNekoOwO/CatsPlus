package cuteneko.catsplus.fabric.registry;

import cuteneko.catsplus.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockRegistry {
    private static void register(Identifier identifier, Block block) {
        Registry.register(Registries.BLOCK, identifier, block);
    }

    public static void register() {
        register(ModBlocks.ID_CAT_RESURRECTION_STATION, ModBlocks.CAT_RESURRECTION_STATION);
    }
}
