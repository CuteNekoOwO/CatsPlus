package cuteneko.catsplus.mixin.client.dancing;

import cuteneko.catsplus.CatsPlusData;
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
    public void catsplus$setupAnim(T cat, float f, float g, float h, float i, float j, CallbackInfo ci) {
        // Random shake head to left or right.
        var bias = cat.getUUID().getLeastSignificantBits() % 2 == 0 ? -1 : 1;

        var geniusCat = CatsPlusData.getGeniusCat(cat);
        if (geniusCat.isSoundPlaying()) {
            this.head.xRot = Mth.sin(cat.age * bias) * 0.3f;
            this.head.yRot = Mth.cos(cat.age * bias) * -0.3f;
        }
    }
}
