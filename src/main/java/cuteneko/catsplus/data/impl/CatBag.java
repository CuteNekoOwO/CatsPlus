package cuteneko.catsplus.data.impl;

import cuteneko.catsplus.data.ICatBag;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class CatBag extends CatContainer implements ICatBag {
    public CatBag(ItemStack stack) {
        super(stack);
    }
}
