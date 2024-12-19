package cuteneko.catsplus.mixin.favorability;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.utility.ParticleHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "hurt", at = @At("RETURN"))
    private void catsplus$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof Cat cat) {
            if (cir.getReturnValue()) {
                if (source.getEntity() instanceof Player player) {
                    var geniusCat = CatsPlusData.getGeniusCat(cat);

                    if (cat.isOwnedBy(player)) {
                        geniusCat.subIntimacyWith(player, (int) amount * 5);

                        if (geniusCat.getIntimacyWith(player) < -100) {
                            ParticleHelper.catLeave(cat);
                            cat.setTame(false, true);
                        } else {
                            ParticleHelper.catAngry(cat);
                        }
                    } else {
                        geniusCat.subIntimacyWith(player, (int) amount * 2);
                        ParticleHelper.catAngry(cat);
                    }
                }
            }
        }
    }
}
