package cuteneko.catsplus.mixin;

import cuteneko.catsplus.effect.MyEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @ModifyVariable(method = "playAmbientSound", at = @At("STORE"), ordinal = 0)
    private SoundEvent playAmbientSound(SoundEvent soundEvent) {
        if(this.hasStatusEffect(MyEffects.CATTIFY)) return SoundEvents.ENTITY_CAT_STRAY_AMBIENT;
        return soundEvent;
    }

}
