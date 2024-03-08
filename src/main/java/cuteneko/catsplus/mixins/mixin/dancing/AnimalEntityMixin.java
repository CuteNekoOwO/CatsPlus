package cuteneko.catsplus.mixins.mixin.dancing;

import cuteneko.catsplus.mixins.bridge.dancing.IMusicianCat;
import net.minecraft.block.Blocks;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin {
    @Inject(method = "tickMovement", at = @At("TAIL"))
    private void afterTickMovement(CallbackInfo ci) {
        if ((Object) this instanceof CatEntity cat) {
            var source = ((IMusicianCat) cat).catsplus$getSoundSource();

            if (source == null
                    || !source.isWithinDistance(cat.getPos(), 5)
                    || !cat.getWorld().getBlockState(source).isOf(Blocks.JUKEBOX)) {
                ((IMusicianCat) cat).catsplus$setSoundSource(null);
            }
        }
    }
}
