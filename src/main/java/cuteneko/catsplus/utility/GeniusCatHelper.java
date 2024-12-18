package cuteneko.catsplus.utility;

import cuteneko.catsplus.tag.ModItemTags;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.crafting.Ingredient;

public class GeniusCatHelper {
    public static final Ingredient TAMED_CAT_FOODS = Ingredient.of(ModItemTags.COOKED_FISHES);

    public static void produceParticles(LivingEntity entity, ParticleOptions parameters) {
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
