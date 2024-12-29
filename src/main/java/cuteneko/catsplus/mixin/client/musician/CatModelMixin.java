package cuteneko.catsplus.mixin.client.musician;

import cuteneko.catsplus.bridge.ICatBridge;
import net.minecraft.client.model.CatModel;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Cat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CatModel.class)
public abstract class CatModelMixin<T extends Cat>
        extends OcelotModel<T> {

    public CatModelMixin(ModelPart root) {
        super(root);
    }

    @Inject(method = "setupAnim(Lnet/minecraft/world/entity/animal/Cat;FFFFF)V", at = @At("TAIL"))
    public void catsplus$setupAnim(T cat, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        // Random shake head to left or right.

        var bridge = (ICatBridge) cat;
        if (!bridge.catsplus$isSoundPlaying()) {
            return;
        }

        var type = Math.floorMod(cat.getUUID().getLeastSignificantBits(), 4);

        if (type == 0 || type == 1) {
            var bias = type == 0 ? -0.8F : 0.8F;
            this.head.xRot = Mth.sin(ageInTicks * bias) * 0.3f;
            this.head.yRot = Mth.cos(ageInTicks * bias) * -0.3f;
        } else if (type == 2 || type == 3) {
            var bias = type == 2 ? -0.8F : 0.8F;
            this.head.xRot = Mth.cos(ageInTicks * bias) * 0.3f;
            this.head.yRot = Mth.sin(ageInTicks * bias) * -0.3f;
        }
    }
}
