package cuteneko.catsplus.item;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.item.group.ModItemGroups;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(CatsPlus.MODID, RegistryKeys.ITEM);

    public static void register() {
        ITEMS.register();
    }

    public static final RegistrySupplier<Item> CAT_BAG = ITEMS.register("cat_bag", CatBagItem::new);
    public static final RegistrySupplier<Item> TOTEMEOW = ITEMS.register("totemeow", () -> new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON).arch$tab(ModItemGroups.CATS_PLUS)));
    public static final RegistrySupplier<Item> CAT_SPIRIT = ITEMS.register("cat_spirit", CatSpiritItem::new);
    public static final RegistrySupplier<Item> FANG_LUO = ITEMS.register("fang_luo", FangLuoItem::new);
}
