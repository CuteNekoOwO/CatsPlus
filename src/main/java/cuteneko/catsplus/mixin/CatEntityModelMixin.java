package cuteneko.catsplus.mixin;

import cuteneko.catsplus.accessor.CatEntityMixinAccessor;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CatEntityModel;
import net.minecraft.client.render.entity.model.OcelotEntityModel;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CatEntityModel.class)
public abstract class CatEntityModelMixin<T extends CatEntity>
        extends OcelotEntityModel<T> {

    public CatEntityModelMixin(ModelPart root) {
        super(root);
    }

//    @Inject(method = "animateModel(Lnet/minecraft/entity/passive/CatEntity;FFF)V", at = @At("TAIL"))
//    public void animateModel(T catEntity, float f, float g, float h, CallbackInfo ci) {
//        System.out.println(this.headDownAnimation);
//    }

    @Inject(method = "setAngles(Lnet/minecraft/entity/passive/CatEntity;FFFFF)V", at = @At("TAIL"))
    public void setAngles(T catEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        if(!((CatEntityMixinAccessor)catEntity).isSongPlaying()) return;
        this.head.pitch = MathHelper.sin(catEntity.age) * 0.3f;
        this.head.yaw = MathHelper.cos(catEntity.age) * 0.3f;
    }
}