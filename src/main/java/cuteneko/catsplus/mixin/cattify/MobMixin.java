package cuteneko.catsplus.mixin.cattify;

import cuteneko.catsplus.utility.CattifyHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {
    protected MobMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "getAmbientSound", at = @At("RETURN"), cancellable = true)
    private void catsplus$getAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (CattifyHelper.cattified(this)) {
            cir.setReturnValue(SoundEvents.CAT_STRAY_AMBIENT);
        }
    }
}
