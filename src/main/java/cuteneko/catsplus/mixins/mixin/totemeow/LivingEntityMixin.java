package cuteneko.catsplus.mixins.mixin.totemeow;

import cuteneko.catsplus.CatsPlus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void beforeTryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof CatEntity cat) {
            var geniusCat = CatsPlus.getInstance().getPlatform().getGeniusCat((CatEntity) (Object) this);

            if (geniusCat.hasTotem()) {
                cat.setHealth(1.0f);
                cat.clearStatusEffects();
                cat.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                cat.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
                cat.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
                cat.getWorld().sendEntityStatus(this, EntityStatuses.USE_TOTEM_OF_UNDYING);

                geniusCat.setTotem(false);
                cir.setReturnValue(true);
            }
        }
    }
}
