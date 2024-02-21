package cuteneko.catsplus.fabric;

import cuteneko.catsplus.fabric.platform.CatPlayer;
import cuteneko.catsplus.fabric.platform.GeniusCat;
import cuteneko.catsplus.platform.ICatPlayer;
import cuteneko.catsplus.platform.IGeniusCat;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CatsPlusPlatformImpl {
    public static ICatPlayer getCatPlayer(PlayerEntity player) {
        return new CatPlayer(player);
    }

    public static IGeniusCat getGeniusCat(CatEntity cat) {
        return new GeniusCat(cat);
    }
}
