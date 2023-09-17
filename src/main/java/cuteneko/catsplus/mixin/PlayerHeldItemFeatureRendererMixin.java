package cuteneko.catsplus.mixin;

import cuteneko.catsplus.item.MyItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.PlayerHeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerHeldItemFeatureRenderer.class)
public abstract class PlayerHeldItemFeatureRendererMixin<T extends PlayerEntity, M extends EntityModel<T> & ModelWithArms & ModelWithHead> {
    @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
    private void catsplus$beforeRenderItem(LivingEntity entity, ItemStack stack,
                                           ModelTransformationMode transformationMode, Arm arm, MatrixStack matrices,
                                           VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (entity.getMainHandStack().isOf(MyItems.FANG_LUO) || entity.getOffHandStack().isOf(MyItems.FANG_LUO)) {
            if (!stack.isOf(MyItems.FANG_LUO)) {
                ci.cancel();
            }
        }
    }
}
