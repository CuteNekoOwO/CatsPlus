package cuteneko.catsplus.forge;

import cuteneko.catsplus.data.ICatPlayer;
import cuteneko.catsplus.data.IGeniusCat;
import cuteneko.catsplus.forge.capability.ModCapabilities;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CatsPlusDataImpl {
    public static ICatPlayer getCatPlayer(PlayerEntity player) {
        return player.getCapability(ModCapabilities.CAT_PLAYER).orElseThrow(RuntimeException::new);
    }

    public static IGeniusCat getGeniusCat(CatEntity cat) {
        return cat.getCapability(ModCapabilities.GENIUS_CAT).orElseThrow(RuntimeException::new);
    }
}
