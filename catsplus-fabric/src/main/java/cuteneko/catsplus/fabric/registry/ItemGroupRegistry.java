package cuteneko.catsplus.fabric.registry;

import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.item.group.ModItemGroups;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ItemGroupRegistry {
    public static void register() {
        Registry.register(Registries.ITEM_GROUP, ModItemGroups.ID_CATS_PLUS_GROUP, ModItemGroups.buildCatsPlusGroup(FabricItemGroup.builder()));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.add(ModItems.CAT_BAG));
    }
}
