package cuteneko.catsplus.mixin;

import cuteneko.catsplus.CatsPlusData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TamableAnimal.class)
public abstract class TamableAnimalMixin {
    @Inject(method = "tame", at = @At(value = "TAIL"))
    public void catsplus$tame(Player player, CallbackInfo ci) {
        if ((Object) this instanceof Cat cat) {
            var geniusCat = CatsPlusData.getGeniusCat(cat);
            geniusCat.addIntimacyWith(player, 30);
        }
    }
}
