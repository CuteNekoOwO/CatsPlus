package cuteneko.catsplus.mixin.cattify;

import com.mojang.blaze3d.vertex.PoseStack;
import cuteneko.catsplus.client.entity.CatPlayerRenderer;
import cuteneko.catsplus.utility.CattifyHelper;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {

    @Unique
    private CatPlayerRenderer catsplus$catPlayerRenderer;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void catsplus$init(EntityRendererProvider.Context context, boolean useSlimModel, CallbackInfo ci) {
        catsplus$catPlayerRenderer = new CatPlayerRenderer(context);
    }

    @Inject(
            method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void catsplus$render(AbstractClientPlayer entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        if (CattifyHelper.cattified(entity)) {
            catsplus$catPlayerRenderer.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
            ci.cancel();
        }
    }
}
