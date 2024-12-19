package cuteneko.catsplus.utility;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;

public class ParticleHelper {
    public static void catHappy(Cat cat) {
        showHappy(cat);
    }

    public static void catAngry(Cat cat) {
        showAngry(cat);
    }

    public static void catLeave(Cat cat) {
        showFailed(cat);
    }

    public static void showHappy(LivingEntity entity) {
        produceParticles(entity, ParticleTypes.HEART);
    }

    public static void showAngry(LivingEntity entity) {
        produceParticles(entity, ParticleTypes.ANGRY_VILLAGER);
    }

    public static void showFailed(LivingEntity entity) {
        produceParticles(entity, ParticleTypes.SMOKE);
    }

    private static void produceParticles(LivingEntity entity, ParticleOptions parameters) {
        for (int i = 0; i < 5; ++i) {
            double d = entity.getRandom().nextGaussian() * 0.02;
            double e = entity.getRandom().nextGaussian() * 0.02;
            double f = entity.getRandom().nextGaussian() * 0.02;
            entity.level().addParticle(parameters,
                    entity.getRandomX(1.0), entity.getRandomY() + 1.0,
                    entity.getRandomZ(1.0), d, e, f);
        }
    }
}
