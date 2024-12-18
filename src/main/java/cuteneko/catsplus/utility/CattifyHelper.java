package cuteneko.catsplus.utility;

import cuteneko.catsplus.effect.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

public class CattifyHelper {
    public static final Predicate<LivingEntity> PREDICATE_CATTIFIED_PLAYER = entity -> entity instanceof Player && cattified(entity);

    public static boolean cattified(LivingEntity entity) {
        return entity.hasEffect(ModEffects.CATTIFY);
    }
}
