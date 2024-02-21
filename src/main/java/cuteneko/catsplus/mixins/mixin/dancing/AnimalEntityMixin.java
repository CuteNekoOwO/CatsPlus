package cuteneko.catsplus.mixins.mixin.dancing;

import cuteneko.catsplus.CatsPlusPlatform;
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
            var geniusCat = CatsPlusPlatform.getGeniusCat(cat);

            if (geniusCat.getSongSource() == null
                    || !geniusCat.getSongSource().isWithinDistance(cat.getPos(), 3.46)
                    || !cat.getWorld().getBlockState(geniusCat.getSongSource()).isOf(Blocks.JUKEBOX)) {
                geniusCat.songStopPlay();
            }
        }
    }
}
