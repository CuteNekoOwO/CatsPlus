package cuteneko.catsplus.mixin;

import cuteneko.catsplus.impl.PlayerEntityMixinImpl;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(ActiveTargetGoal.class)
public abstract class ActiveTargetGoalMixin extends TrackTargetGoal{
    @Shadow public TargetPredicate targetPredicate;

    private static final Predicate<LivingEntity> EXCEPT_CATTIFY_PLAYER = entity -> (entity instanceof PlayerEntity) && !((PlayerEntityMixinImpl)entity).isCat();

    public ActiveTargetGoalMixin(MobEntity mob, boolean checkVisibility, boolean checkNavigable) {
        super(mob, checkVisibility, checkNavigable);
    }

    @Inject(method = "<init>(Lnet/minecraft/entity/mob/MobEntity;Ljava/lang/Class;Z)V", at = @At("TAIL"))
    private void targetGoal(MobEntity mob, Class targetClass, boolean checkVisibility, CallbackInfo ci) {
        if(!(mob instanceof HostileEntity)) return;
        this.targetPredicate.setPredicate(EXCEPT_CATTIFY_PLAYER);
    }
}
