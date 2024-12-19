package cuteneko.catsplus.mixin.dancing;

import cuteneko.catsplus.CatsPlusData;
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
            var geniusCat = CatsPlusData.getGeniusCat(cat);

            if (playing) {
                geniusCat.setSoundPlaying(jukebox);
            } else {
                geniusCat.setSoundStopped();
            }
        }
    }
}
