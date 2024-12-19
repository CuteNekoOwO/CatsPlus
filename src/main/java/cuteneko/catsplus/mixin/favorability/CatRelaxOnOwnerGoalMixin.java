package cuteneko.catsplus.mixin.favorability;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.utility.ParticleHelper;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.world.entity.animal.Cat$CatRelaxOnOwnerGoal")
public abstract class CatRelaxOnOwnerGoalMixin {
    @Shadow @Final private Cat cat;

    @Shadow @Nullable private Player ownerPlayer;

    @Inject(method = "giveMorningGift", at = @At("HEAD"))
    private void catsplus$giveMorningGift(CallbackInfo ci) {
        var geniusCat = CatsPlusData.getGeniusCat(cat);

        geniusCat.addIntimacyWith(ownerPlayer, 5);
        ParticleHelper.catHappy(cat);
        // Todo: qyl27: Make a wish to the cat? The higher favorability, the better item will got.
    }
}
