package cuteneko.catsplus.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if(getFirstPassenger() instanceof CatEntity && !isOnGround()) {
            var cat = getFirstPassenger();
            cat.stopRiding();
            if(this.world.isClient()) return;
            var vel = this.getVelocity();
            cat.addVelocity(vel.x * 5, vel.y, vel.z * 5);
            System.out.println(cat.getVelocity());
        }
    }

    @Override
    public double getMountedHeightOffset() {
        var dimensions = this.getType().getDimensions();
        return (double)dimensions.height * 0.9;
    }
}
