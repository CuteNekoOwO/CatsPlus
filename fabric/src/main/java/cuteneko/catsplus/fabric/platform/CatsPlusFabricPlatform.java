package cuteneko.catsplus.fabric.platform;

import cuteneko.catsplus.platform.ICatPlayer;
import cuteneko.catsplus.platform.ICatsPlusPlatform;
import cuteneko.catsplus.platform.IGeniusCat;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CatsPlusFabricPlatform implements ICatsPlusPlatform {
    @Override
    public ICatPlayer getCatPlayer(PlayerEntity player) {
        return new CatPlayer(player);
    }

    @Override
    public IGeniusCat getGeniusCat(CatEntity cat) {
        return new GeniusCat(cat);
    }
}
