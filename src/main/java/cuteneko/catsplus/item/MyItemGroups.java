package cuteneko.catsplus.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
public class MyItemGroups {
    private static final ItemGroup CATS_PLUS_GROUP = FabricItemGroup.builder(new Identifier("catsplus", "catsplus_group"))
            .icon(() -> new ItemStack(MyItems.CAT_BAG))
            .entries((enabledFeatures, entries, operatorEnabled) -> {
                entries.add(MyItems.CAT_BAG);
                entries.add(MyItems.TOTEMEOW);
            })
            .build();

}
