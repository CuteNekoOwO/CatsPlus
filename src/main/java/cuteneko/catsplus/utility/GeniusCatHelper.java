package cuteneko.catsplus.utility;

import cuteneko.catsplus.tag.ModItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.recipe.Ingredient;

public class GeniusCatHelper {
    public static final Ingredient TAMED_CAT_FOODS = Ingredient.fromTag(ModItemTags.COOKED_FISHES);

    public static void produceParticles(LivingEntity entity, ParticleEffect parameters) {
        for (int i = 0; i < 5; ++i) {
            double d = entity.getRandom().nextGaussian() * 0.02;
            double e = entity.getRandom().nextGaussian() * 0.02;
            double f = entity.getRandom().nextGaussian() * 0.02;
            entity.getEntityWorld().addParticle(parameters,
                    entity.getParticleX(1.0), entity.getRandomBodyY() + 1.0,
                    entity.getParticleZ(1.0), d, e, f);
        }
    }
}
