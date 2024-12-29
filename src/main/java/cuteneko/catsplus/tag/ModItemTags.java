package cuteneko.catsplus.tag;

import cuteneko.catsplus.CatsPlus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> COOKED_FISHES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(CatsPlus.MODID, "cooked_fishes"));
    public static final TagKey<Item> CAT_INTERACTABLE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(CatsPlus.MODID, "cat_interactable"));
}
