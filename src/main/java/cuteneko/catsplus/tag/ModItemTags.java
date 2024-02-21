package cuteneko.catsplus.tag;

import cuteneko.catsplus.CatsPlus;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> COOKED_FISHES = TagKey.of(RegistryKeys.ITEM, new Identifier(CatsPlus.MODID, "cooked_fishes"));
}
