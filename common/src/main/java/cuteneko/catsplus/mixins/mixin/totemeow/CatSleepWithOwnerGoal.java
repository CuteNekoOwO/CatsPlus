package cuteneko.catsplus.mixins.mixin.totemeow;

import cuteneko.catsplus.CatsPlus;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.passive.CatEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.entity.passive.CatEntity$SleepWithOwnerGoal")
public abstract class CatSleepWithOwnerGoal {
    @Shadow
    @Final
    private CatEntity cat;

    @Inject(method = "dropMorningGifts", at = @At("TAIL"))
    private void afterDropMorningGifts(CallbackInfo ci) {
        var geniusCat = CatsPlus.getInstance().getPlatform().getGeniusCat(cat);

        if (!geniusCat.canRespawn()) {
            cat.getWorld().sendEntityStatus(cat, EntityStatuses.ADD_VILLAGER_HEART_PARTICLES);
            geniusCat.setCanRespawn(true);
        }
    }
}
