package cuteneko.catsplus.mixin.client.paper_model;

import cuteneko.catsplus.item.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends LivingEntity> {
    @Shadow
    @Final
    public ModelPart rightArm;

    @Shadow
    @Final
    public ModelPart leftArm;

    @Shadow
    @Final
    public ModelPart head;

    @Inject(method = "poseLeftArm",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/model/AnimationUtils;animateCrossbowHold(Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;Z)V",
                    shift = At.Shift.AFTER),
            cancellable = true)
    private void catsplus$poseLeftArm(T entity, CallbackInfo ci) {
        if (entity.getOffhandItem().is(ModItems.FANG_LUO.get())) {
            catsplus$showHandheldPose(false);
            ci.cancel();
        }
    }

    @Inject(method = "poseRightArm",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/model/AnimationUtils;animateCrossbowHold(Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;Z)V",
                    shift = At.Shift.AFTER),
            cancellable = true)
    private void catsplus$poseRightArm(T entity, CallbackInfo ci) {
        if (entity.getOffhandItem().is(ModItems.FANG_LUO.get())) {
            catsplus$showHandheldPose(true);
            ci.cancel();
        }
    }

    @Unique
    private void catsplus$showHandheldPose(boolean rightArmed) {
        rightArm.xRot = rightArmed ? -0.95f : -0.9f;
        rightArm.yRot = (float) (-Math.PI / 8);
        leftArm.xRot = rightArmed ? -0.9f : -0.95f;
        leftArm.yRot = (float) (Math.PI / 8);
    }
}
