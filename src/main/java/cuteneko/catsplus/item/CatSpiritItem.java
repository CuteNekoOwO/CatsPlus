package cuteneko.catsplus.item;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;

public class CatSpiritItem extends Item {
    public CatSpiritItem() {
        super(new Item.Settings()
                .maxCount(1)
                .fireproof()
                .rarity(Rarity.EPIC));
    }

    @Override
    public Text getName(ItemStack stack) {
        var spirit = CatsPlusData.getCatSpirit(stack);
        if (spirit.hasCat()) {
            if (spirit.hasCustomCatName()) {
                return Text.translatable(Constants.MESSAGE_CAT_SPIRIT_NAME, spirit.getCustomCatName().getString());
            }
        }

        return super.getName(stack);
    }
}
