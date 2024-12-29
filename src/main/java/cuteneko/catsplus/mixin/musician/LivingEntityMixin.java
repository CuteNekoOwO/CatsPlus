package cuteneko.catsplus.mixin.musician;

import cuteneko.catsplus.bridge.ICatBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "setRecordPlayingNearby", at = @At("TAIL"))
    private void catsplus$setRecordPlayingNearby(BlockPos jukebox, boolean playing, CallbackInfo ci) {
        if ((Object) this instanceof Cat cat) {
            var bridge = (ICatBridge) cat;

            if (!bridge.catsplus$isSoundPlaying()) {
                if (playing) {
                    bridge.catsplus$startSound(jukebox);
                } else {
                    bridge.catsplus$stopSound();
                }
            }
        }
    }
}
