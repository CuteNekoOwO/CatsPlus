package cuteneko.catsplus.mixins.mixin.cattify;

import cuteneko.catsplus.CatsPlus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract boolean isSneaking();

    @Inject(method = "getMountedHeightOffset", at = @At("HEAD"), cancellable = true)
    private void beforeGetMountedHeightOffset(CallbackInfoReturnable<Double> cir) {
        if ((Object) this instanceof PlayerEntity player) {
            var catPlayer = CatsPlus.getInstance().getPlatform().getCatPlayer(player);
            if (catPlayer.isCat()) {
                var dimensions = player.getType().getDimensions();
                cir.setReturnValue((double) dimensions.height * 0.9);
            }
        }
    }

    @Inject(method = "getStandingEyeHeight", at = @At("HEAD"), cancellable = true)
    private void beforeGetStandingEyeHeight(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof PlayerEntity player) {
            var catPlayer = CatsPlus.getInstance().getPlatform().getCatPlayer(player);
            if (catPlayer.isCat()) {
                cir.setReturnValue(catPlayer.getCatEntity().getStandingEyeHeight() + (isSneaking() ? 0 : 0.2f));
            }
        }
    }
}
