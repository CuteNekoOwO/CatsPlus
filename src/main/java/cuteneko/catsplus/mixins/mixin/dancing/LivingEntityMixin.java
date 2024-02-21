package cuteneko.catsplus.mixins.mixin.dancing;

import cuteneko.catsplus.CatsPlusData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "setNearbySongPlaying", at = @At("HEAD"))
    private void beforeSetNearbySongPlaying(BlockPos songPosition, boolean playing, CallbackInfo ci) {
        if ((Object) this instanceof CatEntity cat) {
            var geniusCat = CatsPlusData.getGeniusCat(cat);
            geniusCat.songStartPlay(songPosition);
        }
    }
}
