package cuteneko.catsplus.mixin.dancing;

import cuteneko.catsplus.data.entity.GeniusCat;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.animal.Cat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Cat.class)
public abstract class CatMixin {
    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void catsplus$defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(GeniusCat.SOUND_PLAYING, false);
        builder.define(GeniusCat.SOUND_SOURCE, BlockPos.ZERO);
    }
}
