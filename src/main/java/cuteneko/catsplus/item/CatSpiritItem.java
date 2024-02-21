package cuteneko.catsplus.item;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class CatSpiritItem extends Item {
    public CatSpiritItem() {
        super(new Item.Settings()
                .maxCount(1)
                .fireproof()
                .rarity(Rarity.EPIC));
    }
}
