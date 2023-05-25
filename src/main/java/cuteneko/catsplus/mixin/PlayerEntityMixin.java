package cuteneko.catsplus.mixin;

import cuteneko.catsplus.effect.MyEffects;
import cuteneko.catsplus.impl.PlayerEntityMixinImpl;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityMixinImpl {


    public CatEntity cat;

    public EntityDimensions playerDimensions;

    public float playerEyeHeight;

    @Override
    public boolean isCat() {
        return this.hasStatusEffect(MyEffects.CATTIFY);
//        return this.isOnGround();
    }

    @Override
    public CatEntity getCat() {
        if(this.cat == null){
            int variant = (int) (this.uuid.getLeastSignificantBits() % 11);
            if(variant < 0) variant += 11;
            var key = switch (variant) {
                case 0 -> CatVariant.TABBY;
                case 1 -> CatVariant.BLACK;
                case 2 -> CatVariant.RED;
                case 3 -> CatVariant.SIAMESE;
                case 4 -> CatVariant.BRITISH_SHORTHAIR;
                case 5 -> CatVariant.CALICO;
                case 6 -> CatVariant.PERSIAN;
                case 7 -> CatVariant.RAGDOLL;
                case 8 -> CatVariant.WHITE;
                case 9 -> CatVariant.JELLIE;
                case 10 -> CatVariant.ALL_BLACK;
                default -> throw new IllegalArgumentException("Invalid variant: " + variant);
            };
            this.cat = EntityType.CAT.create(this.world);
            if (this.cat != null) this.cat.setVariant(Registries.CAT_VARIANT.get(key));

        }
        return this.cat;
    }


    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if(getFirstPassenger() instanceof CatEntity && (!isOnGround() || isCat())) {
            var cat = getFirstPassenger();
            cat.stopRiding();
            if(this.world.isClient()) return;
            var vel = this.getVelocity();
            cat.addVelocity(vel.x * 5, vel.y, vel.z * 5);
            System.out.println(cat.getVelocity());
        }
        if(this.firstUpdate) {
            this.playerDimensions = this.dimensions;
            this.playerEyeHeight = this.standingEyeHeight;
        }
        if(this.isCat()) {
            var cat = this.getCat();
            this.dimensions = cat.dimensions;
//            this.setBoundingBox(cat.getBoundingBox());
            this.standingEyeHeight = this.getStandingEyeHeight();
        } else {
            this.dimensions = this.playerDimensions;
            this.standingEyeHeight = this.playerEyeHeight - (this.isSneaking()? 0.25f : 0);
        }
    }

//    @Override
//    public Box getVisibilityBoundingBox() {
//        if(!this.isCat()) return super.getVisibilityBoundingBox();
//        return this.getCat().getVisibilityBoundingBox();
//    }



    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        if(!isCat()) return super.getDimensions(pose);
        return getCat().getDimensions(this.getPose());
    }

    @Override
    public double getMountedHeightOffset() {
        var dimensions = this.getType().getDimensions();
        return (double)dimensions.height * 0.9;

    }


    @Override
    public float getStandingEyeHeight() {
        if(!this.isCat()) return super.getStandingEyeHeight();
        return getCat().getStandingEyeHeight() + (this.isSneaking()? 0.1f : 0.2f);
    }

//    @Override
//    public float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
//        if(!this.isCat()) return super.getEyeHeight(pose, dimensions);
//        return getCat().getEyeHeight(this.getPose(), dimensions);
//    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    private void handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if(this.isCat()) cir.setReturnValue(false);
    }

}
