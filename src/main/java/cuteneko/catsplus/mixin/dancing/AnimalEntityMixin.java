package cuteneko.catsplus.mixin.dancing;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.bridge.IMusicianCat;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Animal.class)
public abstract class AnimalEntityMixin {
    @Inject(method = "aiStep", at = @At("TAIL"))
    private void afterTickMovement(CallbackInfo ci) {
        if ((Object) this instanceof Cat cat) {
            var source = CatsPlusData.getMusicianCat(cat).getSoundSource();

            if (source == null
                    || !source.closerThan(cat.blockPosition(), 5)
                    || !cat.level().isLoaded(source)
                    || !cat.level().getBlockState(source).is(Blocks.JUKEBOX)) {
                ((IMusicianCat) cat).catsplus$setSoundSource(null);
            }
        }
    }
}
