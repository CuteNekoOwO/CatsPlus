package cuteneko.catsplus.mixins.mixin.cattify;

import cuteneko.catsplus.CatsPlusData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract boolean isSneaking();

    @Inject(method = "getPassengerAttachmentPos", at = @At("HEAD"), cancellable = true)
    private void beforeGetMountedHeightOffset(CallbackInfoReturnable<Vector3f> cir) {
        if ((Object) this instanceof PlayerEntity player) {
            var catPlayer = CatsPlusData.getCatPlayer(player);
            if (catPlayer.isCat()) {
                var dimensions = player.getType().getDimensions();
                cir.setReturnValue(new Vector3f(0, dimensions.height * 0.9F, 0));
            }
        }
    }

    @Inject(method = "getStandingEyeHeight", at = @At("HEAD"), cancellable = true)
    private void beforeGetStandingEyeHeight(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof PlayerEntity player) {
            var catPlayer = CatsPlusData.getCatPlayer(player);
            if (catPlayer.isCat()) {
                cir.setReturnValue(catPlayer.getCatEntity().getStandingEyeHeight() + (isSneaking() ? 0 : 0.2F));
            }
        }
    }
}
