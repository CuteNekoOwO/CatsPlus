package cuteneko.catsplus;

import cuteneko.catsplus.block.ModBlocks;
import cuteneko.catsplus.data.component.ModComponents;
import cuteneko.catsplus.effect.ModEffects;
import cuteneko.catsplus.effect.potion.ModPotions;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.item.group.ModItemGroups;
import cuteneko.catsplus.listener.ModListeners;
import net.minecraft.resources.ResourceLocation;

public class CatsPlus {
    public static final String MODID = "catsplus";

    public static void init() {
        ModComponents.register();
        ModItemGroups.register();
        ModBlocks.register();
        ModItems.register();
        ModEffects.register();
        ModPotions.register();
        ModListeners.register();
    }

    public static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(CatsPlus.MODID, path);
    }
}
