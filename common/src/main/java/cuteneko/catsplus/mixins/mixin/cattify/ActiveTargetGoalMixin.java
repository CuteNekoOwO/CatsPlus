package cuteneko.catsplus.mixins.mixin.cattify;

import cuteneko.catsplus.utility.CatPlayerHelper;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ActiveTargetGoal.class)
public abstract class ActiveTargetGoalMixin extends TrackTargetGoal {
    @Shadow
    public TargetPredicate targetPredicate;

    public ActiveTargetGoalMixin(MobEntity mob, boolean checkVisibility, boolean checkNavigable) {
        super(mob, checkVisibility, checkNavigable);
    }

    @Inject(method = "<init>(Lnet/minecraft/entity/mob/MobEntity;Ljava/lang/Class;Z)V", at = @At("TAIL"))
    private void targetGoal(MobEntity mob, Class targetClass, boolean checkVisibility, CallbackInfo ci) {
        if (mob instanceof HostileEntity) {
            this.targetPredicate.setPredicate(CatPlayerHelper.CATTIFY_PLAYER);
        }
    }
}
