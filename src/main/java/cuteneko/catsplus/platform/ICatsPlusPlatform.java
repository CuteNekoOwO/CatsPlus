package cuteneko.catsplus.platform;

import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface ICatsPlusPlatform {
    ICatPlayer getCatPlayer(PlayerEntity player);

    IGeniusCat getGeniusCat(CatEntity cat);
}
