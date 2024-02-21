package cuteneko.catsplus;

import cuteneko.catsplus.platform.ICatPlayer;
import cuteneko.catsplus.platform.IGeniusCat;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CatsPlusPlatform {
    @ExpectPlatform
    public static ICatPlayer getCatPlayer(PlayerEntity player) {
        throw new UnsupportedOperationException();
    }

    @ExpectPlatform
    public static IGeniusCat getGeniusCat(CatEntity cat) {
        throw new UnsupportedOperationException();
    }
}
