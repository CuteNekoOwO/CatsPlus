package cuteneko.catsplus.mixin;

import cuteneko.catsplus.impl.PlayerEntityMixinImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin
        extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>{

    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Redirect(
            method = "Lnet/minecraft/client/render/entity/PlayerEntityRenderer;render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")
    )
    private void onRender(LivingEntityRenderer instance, LivingEntity player, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        var cat = ((PlayerEntityMixinImpl)player).getCat();
        cat.lastLimbDistance = player.lastLimbDistance;
        cat.limbDistance = player.limbDistance;
        cat.limbAngle = player.limbAngle;
//        cat.handSwinging = player.handSwinging;
//        cat.handSwingTicks = player.handSwingTicks;
//        cat.lastHandSwingProgress = player.lastHandSwingProgress;
//        cat.handSwingProgress = player.handSwingProgress;
        cat.bodyYaw = player.bodyYaw;
        cat.prevBodyYaw = player.prevBodyYaw;
        cat.headYaw = player.headYaw;
        cat.prevHeadYaw = player.prevHeadYaw;
//        cat.age = player.age;
        cat.preferredHand = player.preferredHand;
        cat.setOnGround(player.isOnGround());
        cat.setVelocity(player.getVelocity());
        cat.setPitch(player.getPitch());
        cat.prevPitch = player.prevPitch;
//        cat.setPose(player.getPose());
        cat.setPosition(player.getPos());
        cat.setSneaking(false);
        cat.setSitting(player.isSneaking());
        cat.setInSittingPose(player.isInSneakingPose());
        cat.setSprinting(player.isSprinting());
        cat.setAttacking(player.isUsingItem());
        cat.setInSleepingPose(player.isSleeping());



        if(!((PlayerEntityMixinImpl) player).isCat()) {
            super.render((AbstractClientPlayerEntity) player, f, g, matrixStack, vertexConsumerProvider, i);
            return;
        }

        var renderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(cat);
        renderer.render(cat, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
