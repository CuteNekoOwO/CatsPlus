package cuteneko.catsplus.utility;

import cuteneko.catsplus.CatsPlusData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Predicate;

public class CatPlayerHelper {
    public static final Predicate<LivingEntity> CATTIFY_PLAYER = entity -> {
        if (entity instanceof PlayerEntity player) {
            var catPlayer = CatsPlusData.getCatPlayer(player);
            return catPlayer.isCat();
        }
        return false;
    };
}
