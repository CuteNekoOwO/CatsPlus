package cuteneko.catsplus.utility;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.recipe.Ingredient;

public class GeniusCatHelper {
    // Todo: qyl27: Change it to a cooked_fish tag.
    public static final Ingredient TAMED_CAT_FOODS = Ingredient.ofItems(
            Items.COD,
            Items.SALMON,
            Items.COOKED_COD,
            Items.COOKED_SALMON,
            Items.TROPICAL_FISH);

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
