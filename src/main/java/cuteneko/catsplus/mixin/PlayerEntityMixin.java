package cuteneko.catsplus.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityPassengersSetS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Shadow protected abstract boolean isOnSoulSpeedBlock();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if(getFirstPassenger() instanceof CatEntity && !isOnGround()) {
            var cat = getFirstPassenger();
            cat.collidedSoftly = true;
            cat.stopRiding();
            var vel = this.getVelocity();
            cat.addVelocity(vel.x * 5, vel.y, vel.z * 5);
        }
    }

    @Override
    public double getMountedHeightOffset() {
        var dimensions = this.getType().getDimensions();
        return (double)dimensions.height * 0.9;
    }
}
