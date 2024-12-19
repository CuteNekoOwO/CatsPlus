package cuteneko.catsplus.mixin.cattify;

import cuteneko.catsplus.utility.CattifyHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow public abstract boolean isShiftKeyDown();

    @Inject(method = "getPassengerAttachmentPoint", at = @At("HEAD"), cancellable = true)
    private void catsplus$getPassengerAttachmentPoint(CallbackInfoReturnable<Vector3f> cir) {
        if ((Object) this instanceof Player player) {
            if (CattifyHelper.cattified(player)) {
                var dimensions = EntityType.CAT.getDimensions();
                cir.setReturnValue(new Vector3f(0, dimensions.height() * 0.9F, 0));
            }
        }
    }

    @Inject(method = "getEyeHeight()F", at = @At("HEAD"), cancellable = true)
    private void catsplus$getEyeHeight(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof Player player) {
            if (CattifyHelper.cattified(player)) {
                cir.setReturnValue(EntityType.CAT.getDimensions().eyeHeight() + (isShiftKeyDown() ? -0.1F : 0));
            }
        }
    }

    @Inject(method = "getBbWidth", at = @At("RETURN"), cancellable = true)
    private void catsplus$getBbWidth(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof Player player) {
            if (CattifyHelper.cattified(player)) {
                cir.setReturnValue(EntityType.CAT.getDimensions().width());
            }
        }
    }

    @Inject(method = "getBbHeight", at = @At("RETURN"), cancellable = true)
    private void catsplus$getBbHeight(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof Player player) {
            if (CattifyHelper.cattified(player)) {
                cir.setReturnValue(EntityType.CAT.getDimensions().height() + (isShiftKeyDown() ? -0.2F : 0));
            }
        }
    }
}
