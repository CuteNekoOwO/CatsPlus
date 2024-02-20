package cuteneko.catsplus;

import cuteneko.catsplus.block.ModBlocks;
import cuteneko.catsplus.effect.ModEffects;
import cuteneko.catsplus.effect.potion.ModPotions;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.item.group.ModItemGroups;
import cuteneko.catsplus.platform.ICatsPlusPlatform;

public class CatsPlus {
    public static final String MODID = "catsplus";

    private static CatsPlus INSTANCE;

    private ICatsPlusPlatform platform;

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

    public void setPlatform(ICatsPlusPlatform platform) {
        this.platform = platform;
    }

    public ICatsPlusPlatform getPlatform() {
        return platform;
    }
}
