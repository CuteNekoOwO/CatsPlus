package cuteneko.catsplus.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
public class MyItemGroups {

    private static final ItemGroup CATS_PLUS_GROUP = FabricItemGroup.builder()
        .displayName(Text.translatable("itemGroup.catsplus.catsplus_group"))
        .icon(() -> new ItemStack(MyItems.CAT_BAG))
        .entries((displayContext, entries) -> {
            entries.add(MyItems.CAT_BAG);
            entries.add(MyItems.TOTEMEOW);
            entries.add(MyItems.CAT_RESURRECTION_STATION_ITEM);
        })
        .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, new Identifier("catsplus", "catsplus_group"), CATS_PLUS_GROUP);
    }
}
