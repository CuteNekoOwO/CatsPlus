package cuteneko.catsplus.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffect;

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
        
    }
}
