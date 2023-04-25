package cuteneko.catsplus.mixin;

import cuteneko.catsplus.effect.MyEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @ModifyVariable(method = "playHurtSound", at = @At("STORE"), ordinal = 0)
    private SoundEvent playHurtSound(SoundEvent soundEvent) {
        if(this.hasStatusEffect(MyEffects.CATTIFY)) return SoundEvents.ENTITY_CAT_HURT;
        return soundEvent;
    }

    @ModifyVariable(method = "damage", at = @At("STORE"), ordinal = 0)
    private SoundEvent playDeathSound(SoundEvent soundEvent) {
        if(this.hasStatusEffect(MyEffects.CATTIFY)) return SoundEvents.ENTITY_CAT_DEATH;
        return soundEvent;
    }

}
