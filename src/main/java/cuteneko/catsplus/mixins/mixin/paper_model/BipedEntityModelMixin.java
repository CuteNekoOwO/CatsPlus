package cuteneko.catsplus.mixins.mixin.paper_model;

import cuteneko.catsplus.item.ModItems;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> {
    @Shadow
    @Final
    public ModelPart rightArm;

    @Shadow
    @Final
    public ModelPart leftArm;

    @Shadow
    @Final
    public ModelPart head;

    @Inject(method = "positionLeftArm",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/CrossbowPosing;hold(Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Z)V",
                    shift = At.Shift.AFTER),
            cancellable = true)
    private void catsplus$invokeLeftPosingHold(T entity, CallbackInfo ci) {
        if (entity.getOffHandStack().isOf(ModItems.FANG_LUO.get())) {
            catsplus$showHandheldPose(false);
            ci.cancel();
        }
    }

    @Inject(method = "positionRightArm",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/CrossbowPosing;hold(Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Z)V",
                    shift = At.Shift.AFTER),
            cancellable = true)
    private void catsplus$invokeRightPosingHold(T entity, CallbackInfo ci) {
        if (entity.getMainHandStack().isOf(ModItems.FANG_LUO.get())) {
            catsplus$showHandheldPose(true);
            ci.cancel();
        }
    }

    @Unique
    private void catsplus$showHandheldPose(boolean rightArmed) {
        rightArm.pitch = rightArmed ? -0.95f : -0.9f;
        rightArm.yaw = (float) (-Math.PI / 8);
        leftArm.pitch = rightArmed ? -0.9f : -0.95f;
        leftArm.yaw = (float) (Math.PI / 8);
    }
}
