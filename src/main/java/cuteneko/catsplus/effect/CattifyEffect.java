package cuteneko.catsplus.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.CatEntity;

public class CattifyEffect extends StatusEffect {
    public CattifyEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xFF9CA8);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!(entity instanceof CatEntity cat)) return;
        var effect = cat.getStatusEffect(this);
        assert effect != null;
        var duration = effect.getDuration();
        cat.removeStatusEffect(this);
        cat.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, duration, 0));
    }
}
