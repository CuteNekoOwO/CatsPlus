package cuteneko.catsplus.mixins.mixin.dancing;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.mixins.bridge.dancing.IMusicianCat;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CatEntityModel;
import net.minecraft.client.render.entity.model.OcelotEntityModel;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CatEntityModel.class)
public abstract class CatEntityModelMixin<T extends CatEntity>
        extends OcelotEntityModel<T> {
    public CatEntityModelMixin(ModelPart root) {
        super(root);
    }

    @Inject(method = "setAngles(Lnet/minecraft/entity/passive/CatEntity;FFFFF)V", at = @At("TAIL"))
    public void setAngles(T cat, float f, float g, float h, float i, float j, CallbackInfo ci) {
        // Random shake head to left or right.
        var bias = cat.getUuid().getLeastSignificantBits() % 2 == 0 ? -1 : 1;

        if (((IMusicianCat) cat).catsplus$getSoundSource() != null) {
            this.head.pitch = MathHelper.sin(cat.age * bias) * 0.3f;
            this.head.yaw = MathHelper.cos(cat.age * bias) * -0.3f;
        }
    }
}
