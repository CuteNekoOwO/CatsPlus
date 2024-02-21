package cuteneko.catsplus.fabric;

import cuteneko.catsplus.fabric.data.CatPlayerFabric;
import cuteneko.catsplus.fabric.data.GeniusCatFabric;
import cuteneko.catsplus.data.ICatPlayer;
import cuteneko.catsplus.data.IGeniusCat;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CatsPlusDataImpl {
    public static ICatPlayer getCatPlayer(PlayerEntity player) {
        return new CatPlayerFabric(player);
    }

    public static IGeniusCat getGeniusCat(CatEntity cat) {
        return new GeniusCatFabric(cat);
    }
}
