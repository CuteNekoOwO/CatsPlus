package cuteneko.catsplus.mixin.musician;

import cuteneko.catsplus.bridge.ICatBridge;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Animal.class)
public abstract class AnimalMixin {
    @Inject(method = "aiStep", at = @At("TAIL"))
    private void catsplus$aiStep(CallbackInfo ci) {
        if ((Object) this instanceof Cat cat) {
            var bridge = (ICatBridge) cat;
            if (bridge.catsplus$isSoundPlaying()) {
                var source = bridge.catsplus$getSoundSource();
                if (source == null
                        || !source.closerThan(cat.blockPosition(), 5)
                        || !cat.level().isLoaded(source)
                        || !cat.level().getBlockState(source).is(Blocks.JUKEBOX)) {
                    bridge.catsplus$stopSound();
                }
            }
        }
    }
}
