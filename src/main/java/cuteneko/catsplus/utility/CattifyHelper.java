package cuteneko.catsplus.utility;

import cuteneko.catsplus.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

public class CattifyHelper {
    public static final Predicate<LivingEntity> PREDICATE_CATTIFIED_PLAYER = entity -> entity instanceof Player && cattified(entity);

//    private static Holder<MobEffect> CATTIFY;

    public static boolean cattified(LivingEntity entity) {
//        if (CATTIFY == null) {
//            CATTIFY = BuiltInRegistries.MOB_EFFECT.getHolder(ModEffects.CATTIFY.getId()).get();
//        }

        return entity.hasEffect(ModEffects.CATTIFY);
    }
}
