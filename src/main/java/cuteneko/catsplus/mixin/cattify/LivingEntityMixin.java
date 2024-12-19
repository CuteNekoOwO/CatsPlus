package cuteneko.catsplus.mixin.cattify;

import cuteneko.catsplus.utility.CattifyHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "getHurtSound", at = @At("RETURN"), cancellable = true)
    private void catsplus$getHurtSound(DamageSource damageSource, CallbackInfoReturnable<SoundEvent> cir) {
        if (CattifyHelper.cattified((LivingEntity) (Object) this)) {
            cir.setReturnValue(SoundEvents.CAT_HURT);
        }
    }

    @Inject(method = "getDeathSound", at = @At("RETURN"), cancellable = true)
    private void catsplus$getDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (CattifyHelper.cattified((LivingEntity) (Object) this)) {
            cir.setReturnValue(SoundEvents.CAT_DEATH);
        }
    }
}
