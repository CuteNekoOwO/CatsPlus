package cuteneko.catsplus.item;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.item.group.ModItemGroups;
import cuteneko.catsplus.utility.ComponentHelper;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;

public class CatSpiritItem extends Item {
    public CatSpiritItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .fireResistant()
                .rarity(Rarity.EPIC)
                .arch$tab(ModItemGroups.CATS_PLUS));
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        var catContainer = ComponentHelper.getCatContainer(stack);
        if (catContainer != null) {
            if (catContainer.hasCustomName()) {
                return Component.translatable(Constants.MESSAGE_CAT_SPIRIT_NAME, catContainer.customName().getString());
            }
        }
        return super.getName(stack);
    }
}
