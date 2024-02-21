package cuteneko.catsplus.mixins.mixin.favorability;

import cuteneko.catsplus.CatsPlusPlatform;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "damage", at = @At("RETURN"))
    private void afterDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof CatEntity cat) {
            if (cir.getReturnValue()) {
                if (source.getSource() instanceof PlayerEntity player) {
                    var geniusCat = CatsPlusPlatform.getGeniusCat(cat);

                    if (cat.isOwner(player)) {
                        geniusCat.subFavorability((int) amount * 5, player);
                    } else {
                        geniusCat.subFavorability((int) amount * 2, player);
                    }
                }
            }
        }
    }
}
