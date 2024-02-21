package cuteneko.catsplus.mixins.mixin.cattify;

import cuteneko.catsplus.CatsPlusData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.CatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin
        extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Unique
    private EntityRenderer<? super CatEntity> catsplus$renderer = null;

    @Unique
    private EntityRenderer<? super CatEntity> catsplus$getCatRenderer(CatEntity cat) {
        if (catsplus$renderer == null) {
            catsplus$renderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(cat);
        }

        return catsplus$renderer;
    }

    @Inject(
            method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"),
            cancellable = true
    )
    private void onRender(AbstractClientPlayerEntity player, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        var catPlayer = CatsPlusData.getCatPlayer(player);

        if (catPlayer.isCat()) {
            var cat = catPlayer.getCatEntity();
            cat.limbAnimator.updateLimbs(player.limbAnimator.getSpeed() * 0.4f, 1.0f);
            cat.bodyYaw = player.bodyYaw;
            cat.prevBodyYaw = player.prevBodyYaw;
            cat.headYaw = player.headYaw;
            cat.prevHeadYaw = player.prevHeadYaw;
            cat.age = player.age;
            cat.preferredHand = player.preferredHand;
            cat.setOnGround(player.isOnGround());
            cat.setVelocity(player.getVelocity());
            cat.setPitch(player.getPitch());
            cat.prevPitch = player.prevPitch;
            cat.setPos(player.getX(), player.getY(), player.getZ());
            cat.setSneaking(false);
            cat.setSitting(player.isSneaking());
            cat.setInSittingPose(player.isInSneakingPose());
            cat.setSprinting(player.isSprinting());
            cat.setAttacking(player.isUsingItem());
            cat.setInSleepingPose(player.isSleeping());

            var renderer = catsplus$getCatRenderer(cat);
            renderer.render(cat, f, g, matrixStack, vertexConsumerProvider, i);

            ci.cancel();
        }
    }
}
