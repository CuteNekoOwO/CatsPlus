package cuteneko.catsplus.mixins.mixin.cattify;

import cuteneko.catsplus.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    @Inject(method = "getHurtSound", at = @At("RETURN"), cancellable = true)
    private void playHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        if (hasStatusEffect(ModEffects.CATTIFY.get())) {
            cir.setReturnValue(SoundEvents.ENTITY_CAT_HURT);
        }
    }

    @Inject(method = "getDeathSound", at = @At("RETURN"), cancellable = true)
    private void playDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (hasStatusEffect(ModEffects.CATTIFY.get())) {
            cir.setReturnValue(SoundEvents.ENTITY_CAT_DEATH);
        }
    }
}
