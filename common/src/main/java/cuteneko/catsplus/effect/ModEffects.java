package cuteneko.catsplus.effect;

import cuteneko.catsplus.CatsPlus;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final Identifier ID_CATTIFY = new Identifier(CatsPlus.MODID, "cattify");

    public static final StatusEffect CATTIFY = new CattifyEffect();
}
