package cuteneko.catsplus.mixins.mixin.cattify;

import cuteneko.catsplus.effect.ModEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "getAmbientSound", at = @At("RETURN"), cancellable = true)
    private void afterGetAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (this.hasStatusEffect(ModEffects.CATTIFY.get())) {
            cir.setReturnValue(SoundEvents.ENTITY_CAT_STRAY_AMBIENT);
        }
    }
}
