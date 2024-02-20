package cuteneko.catsplus.effect.potion;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static final Identifier ID_CATTIFY = new Identifier(CatsPlus.MODID, "cattify");
    public static final Identifier ID_LONG_CATTIFY = new Identifier(CatsPlus.MODID, "long_cattify");

    public static final Potion CATTIFY = new Potion(new StatusEffectInstance(ModEffects.CATTIFY, 1800));
    public static final Potion LONG_CATTIFY = new Potion(ID_CATTIFY.getPath(), new StatusEffectInstance(ModEffects.CATTIFY, 4800));
}
