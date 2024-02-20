package cuteneko.catsplus.item.group;

import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final Identifier ID_CATS_PLUS_GROUP = new Identifier("catsplus", "catsplus_group");

    public static ItemGroup buildCatsPlusGroup(ItemGroup.Builder builder) {
        return builder.displayName(Text.translatable(Constants.MESSAGE_CATS_GROUP_TITLE))
                .icon(() -> new ItemStack(ModItems.CAT_BAG))
                .entries((displayContext, entries) -> {
                    entries.add(ModItems.CAT_BAG);
                    entries.add(ModItems.TOTEMEOW);
//                    entries.add(ModItems.CAT_RESURRECTION_STATION_ITEM);
                })
                .build();
    }
}
