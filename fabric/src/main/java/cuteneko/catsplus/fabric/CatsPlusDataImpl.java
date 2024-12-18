package cuteneko.catsplus.fabric;

import cuteneko.catsplus.fabric.data.CatPlayerFabric;
import cuteneko.catsplus.fabric.data.GeniusCatFabric;
import cuteneko.catsplus.data.ICatPlayer;
import cuteneko.catsplus.data.entity.GeniusCat;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CatsPlusDataImpl {
    public static ICatPlayer getCatPlayer(PlayerEntity player) {
        return new CatPlayerFabric(player);
    }

    public static GeniusCat getGeniusCat(CatEntity cat) {
        return new GeniusCatFabric(cat);
    }
}
