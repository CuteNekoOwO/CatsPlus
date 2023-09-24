package cuteneko.catsplus.block;

import cuteneko.catsplus.CatsPlus;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Identifier ID_CAT_RESURRECTION_STATION = new Identifier(CatsPlus.MODID, "cat_resurrection_station");

    public static Block CAT_RESURRECTION_STATION = new CatResurrectionStationBlock(AbstractBlock.Settings.create().hardness(5f));
}
