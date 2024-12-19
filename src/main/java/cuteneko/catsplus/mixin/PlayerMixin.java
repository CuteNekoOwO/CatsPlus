package cuteneko.catsplus.mixin;

import cuteneko.catsplus.utility.CattifyHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (getFirstPassenger() instanceof Cat cat
                && (!onGround() || CattifyHelper.cattified(this))) {
            cat.stopRiding();

            var level = this.level();

            if (level.isClientSide()) {
                return;
            }

            var velocity = this.getDeltaMovement();
            cat.setDeltaMovement(velocity.x * 5, velocity.y, velocity.z * 5);
        }
    }

    // Todo: qyl27: Right click tamed cat to increase favorability.
}
