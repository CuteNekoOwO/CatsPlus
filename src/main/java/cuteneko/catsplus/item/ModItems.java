package cuteneko.catsplus.item;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.item.group.ModItemGroups;
import games.moegirl.sinocraft.sinocore.registry.IRegRef;
import games.moegirl.sinocraft.sinocore.registry.IRegistry;
import games.moegirl.sinocraft.sinocore.registry.RegistryManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {
    public static final IRegistry<Item> ITEMS = RegistryManager.obtain(CatsPlus.MODID, Registries.ITEM);

    public static void register() {
        ITEMS.register();
    }

    public static final IRegRef<Item> CAT_BAG = ITEMS.register("cat_bag", () -> new CatBagItem(new Item.Properties().fireResistant().stacksTo(1).sino$tab(ModItemGroups.CATS_PLUS)));
    public static final IRegRef<Item> TOTEMEOW = ITEMS.register("totemeow", () -> new TotemeowItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).sino$tab(ModItemGroups.CATS_PLUS)));
    public static final IRegRef<Item> FANG_LUO = ITEMS.register("fang_luo", () -> new PaperDollItem(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON)));
    public static final IRegRef<Item> CAT_SPIRIT = ITEMS.register("cat_spirit", CatSpiritItem::new);
}
