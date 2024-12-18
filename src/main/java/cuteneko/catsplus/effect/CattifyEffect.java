package cuteneko.catsplus.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;

public class CattifyEffect extends MobEffect {
    public CattifyEffect() {
        super(MobEffectCategory.NEUTRAL, 0xFF9CA8);
    }

    // Todo: qyl27: use attribute to implement
    // Max Health base value to 10
    // Attack damage base value to 2.5
    // Width 0.6
    // Height 0.7
    // No falling damage
    // Can see invisible mobs

//    @Override
//    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
//        if(!(entity instanceof Cat cat)) {
//            return false;
//        }
//
//        var effect = cat.getStatusEffect(this);
//        assert effect != null;
//        var duration = effect.getDuration();
//        cat.removeStatusEffect(this);
//        cat.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, duration, 0));
//    }
}
