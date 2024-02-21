package cuteneko.catsplus.mixins.mixin.totemeow;

import cuteneko.catsplus.CatsPlusData;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
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

    @Shadow private @Nullable PlayerEntity owner;

    @Inject(method = "dropMorningGifts", at = @At("HEAD"), cancellable = true)
    private void afterDropMorningGifts(CallbackInfo ci) {
        var geniusCat = CatsPlusData.getGeniusCat(cat);

        cat.getWorld().sendEntityStatus(cat, EntityStatuses.ADD_VILLAGER_HEART_PARTICLES);
        // Todo: qyl27: Make a wish to the cat? The higher favorability, the better item will got.
//        if (geniusCat.getFavorability(owner) < 50) {
//
//        }
    }
}
