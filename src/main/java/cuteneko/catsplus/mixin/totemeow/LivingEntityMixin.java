package cuteneko.catsplus.mixin.totemeow;

import cuteneko.catsplus.CatsPlusData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "checkTotemDeathProtection", at = @At("HEAD"), cancellable = true)
    private void catsplus$checkTotemDeathProtection(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof Cat cat) {
            var geniusCat = CatsPlusData.getGeniusCat(cat);

            if (geniusCat.hasTotem()) {
                cat.setHealth(1.0f);
                cat.removeAllEffects();
                cat.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
                cat.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
                cat.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
                cat.level().broadcastEntityEvent(cat, EntityEvent.TALISMAN_ACTIVATE);

                geniusCat.setTotem(false);
                cir.setReturnValue(true);
            }
        }
    }
}
