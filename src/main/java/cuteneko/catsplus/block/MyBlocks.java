package cuteneko.catsplus.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MyBlocks {
    public static Block CAT_RESURRECTION_STATION =
            new CatResurrectionStationBlock(FabricBlockSettings
                    .create()
                    .hardness(5f));

    private static void register(Identifier identifier, Block block) {
        Registry.register(Registries.BLOCK, identifier, block);
    }

    public static void register() {
        register(new Identifier("catsplus", "cat_resurrection_station"), CAT_RESURRECTION_STATION);
    }
}
