package cuteneko.catsplus.mixin;

import cuteneko.catsplus.impl.PlayerEntityMixinImpl;
import cuteneko.catsplus.item.MyItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin
        extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>{

    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    private EntityRenderer<? super CatEntity> renderer = null;

    private EntityRenderer<? super CatEntity> getCatRenderer(CatEntity cat) {
        if(renderer == null) renderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(cat);
        return renderer;
    }

    @Redirect(
            method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")
    )
    private void onRender(LivingEntityRenderer instance, LivingEntity player, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        var cat = ((PlayerEntityMixinImpl)player).getCat();
        cat.limbAnimator.updateLimbs(player.limbAnimator.getSpeed() * 0.4f,1.0f);
//        cat.updateLimbs(false);
//        cat.lastLimbDistance = player.lastLimbDistance;
//        cat.limbDistance = player.limbDistance;
//        cat.limbAngle = player.limbAngle;
//        cat.handSwinging = player.handSwinging;
//        cat.handSwingTicks = player.handSwingTicks;
//        cat.lastHandSwingProgress = player.lastHandSwingProgress;
//        cat.handSwingProgress = player.handSwingProgress;
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



        if(!((PlayerEntityMixinImpl) player).isCat()) {
            super.render((AbstractClientPlayerEntity) player, f, g, matrixStack, vertexConsumerProvider, i);
            return;
        }
        var renderer = getCatRenderer(cat);
        renderer.render(cat, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Inject(method = "getArmPose", at = @At("TAIL"), cancellable = true)
    private static void catsplus$afterGetArmPose(AbstractClientPlayerEntity player, Hand hand,
                                                 CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        if (player.getStackInHand(hand).isOf(MyItems.FANG_LUO)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
