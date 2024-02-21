package cuteneko.catsplus.mixins.mixin.dancing;

import cuteneko.catsplus.CatsPlusData;
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
        var geniusCat = CatsPlusData.getGeniusCat(cat);

        if (geniusCat.isSongPlaying()) {
            this.head.pitch = MathHelper.sin(cat.age) * 0.3f;
            this.head.yaw = MathHelper.cos(cat.age) * -0.3f;
        }
    }
}
