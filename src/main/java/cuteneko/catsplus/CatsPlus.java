package cuteneko.catsplus;

import cuteneko.catsplus.block.ModBlocks;
import cuteneko.catsplus.effect.ModEffects;
import cuteneko.catsplus.effect.potion.ModPotions;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.item.group.ModItemGroups;

public class CatsPlus {
    public static final String MODID = "catsplus";

    private static CatsPlus INSTANCE;

    public CatsPlus() {
        INSTANCE = this;
    }

    public static CatsPlus getInstance() {
        return INSTANCE;
    }

    public void init() {
        ModItemGroups.register();
        ModBlocks.register();
        ModItems.register();
        ModEffects.register();
        ModPotions.register();
    }
}
