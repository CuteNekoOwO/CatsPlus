package cuteneko.catsplus.mixins.mixin.cattify;

import cuteneko.catsplus.CatsPlusPlatform;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Unique
    public EntityDimensions catsplus$playerDimensions;

    @Unique
    public float catsplus$playerEyeHeight;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        var catPlayer = CatsPlusPlatform.getCatPlayer((PlayerEntity) (Object) this);

        if (this.firstUpdate) {
            this.catsplus$playerDimensions = this.dimensions;
            this.catsplus$playerEyeHeight = this.standingEyeHeight;
        }

        if (catPlayer.isCat()) {
            var cat = catPlayer.getCatEntity();
            this.dimensions = cat.dimensions;
            this.standingEyeHeight = this.getStandingEyeHeight();
        } else {
            this.dimensions = this.catsplus$playerDimensions;
            this.standingEyeHeight = this.catsplus$playerEyeHeight - (this.isSneaking() ? 0.25f : 0);
        }
    }

    @Inject(method = "getDimensions", at = @At("HEAD"), cancellable = true)
    private void beforeGetDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> cir) {
        var catPlayer = CatsPlusPlatform.getCatPlayer((PlayerEntity) (Object) this);
        if (catPlayer.isCat()) {
            cir.setReturnValue(catPlayer.getCatEntity().getDimensions(pose));
        }
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    private void handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        var catPlayer = CatsPlusPlatform.getCatPlayer((PlayerEntity) (Object) this);
        if (catPlayer.isCat()) {
            cir.setReturnValue(false);
        }
    }
}
