package cuteneko.catsplus.effect;

import cuteneko.catsplus.CatsPlus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cat;

public class CattifyEffect extends MobEffect {
    private static final ResourceLocation attributeModifierId = CatsPlus.modLoc("effect.cattify");

    // Todo: Can see invisible mobs

    public CattifyEffect() {
        super(MobEffectCategory.NEUTRAL, 0xFF9CA8);
        addAttributeModifier(Attributes.MAX_HEALTH, attributeModifierId, -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.ATTACK_DAMAGE, attributeModifierId, 0.5, AttributeModifier.Operation.ADD_VALUE);
        addAttributeModifier(Attributes.SAFE_FALL_DISTANCE, attributeModifierId, 3, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, attributeModifierId, 0.2, AttributeModifier.Operation.ADD_VALUE);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Cat cat) {
            return MobEffects.REGENERATION.value().applyEffectTick(entity, 0);
        }
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
