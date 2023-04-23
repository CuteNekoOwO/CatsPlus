package cuteneko.catsplus.mixin;

import cuteneko.catsplus.impl.CatEntityMixinImpl;
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
    public void setAngles(T catEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        if(!((CatEntityMixinImpl)catEntity).isSongPlaying()) return;
        this.head.pitch = MathHelper.sin(catEntity.age) * 0.3f;
        this.head.yaw = MathHelper.cos(catEntity.age) * -0.3f;
    }
}
