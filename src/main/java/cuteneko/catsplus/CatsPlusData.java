package cuteneko.catsplus;

import cuteneko.catsplus.data.ICatSpirit;
import cuteneko.catsplus.data.impl.CatSpirit;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.data.ICatBag;
import cuteneko.catsplus.data.ICatPlayer;
import cuteneko.catsplus.data.IGeniusCat;
import cuteneko.catsplus.data.impl.CatBag;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class CatsPlusData {
    @ExpectPlatform
    public static ICatPlayer getCatPlayer(PlayerEntity player) {
        throw new UnsupportedOperationException();
    }

    @ExpectPlatform
    public static IGeniusCat getGeniusCat(CatEntity cat) {
        throw new UnsupportedOperationException();
    }

    public static ICatBag getCatBag(ItemStack bag) {
        if (!bag.isOf(ModItems.CAT_BAG.get())) {
            throw new IllegalArgumentException();
        }

        return new CatBag(bag);
    }

    public static ICatSpirit getCatSpirit(ItemStack spirit) {
        if (!spirit.isOf(ModItems.CAT_SPIRIT.get())) {
            throw new IllegalArgumentException();
        }

        return new CatSpirit(spirit);
    }
}
