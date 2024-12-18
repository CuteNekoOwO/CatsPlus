package cuteneko.catsplus.mixin.cattify;

import cuteneko.catsplus.utility.CattifyHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

//    @Inject(method = "getPassengerAttachmentPoint", at = @At("HEAD"), cancellable = true)
//    private void catsplus$beforeGetMountedHeightOffset(CallbackInfoReturnable<Vector3f> cir) {
//        if ((Object) this instanceof Player player) {
//            if (CattifyHelper.cattified(player)) {
//                var dimensions = player.getType().getDimensions();
//                cir.setReturnValue(new Vector3f(0, dimensions.height() * 0.9F, 0));
//            }
//        }
//    }
//
//    @Inject(method = "getEyeHeight()F", at = @At("HEAD"), cancellable = true)
//    private void catsplus$beforeGetStandingEyeHeight(CallbackInfoReturnable<Float> cir) {
//        if ((Object) this instanceof Player player) {
//            if (CattifyHelper.cattified(player)) {
//                cir.setReturnValue(catPlayer.getCatEntity().getStandingEyeHeight() + (isSneaking() ? 0 : 0.2F));
//            }
//        }
//    }
}
