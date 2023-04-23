package cuteneko.catsplus.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MyEffects {
    public static StatusEffect CATTIFY =  Registry.register(
            Registries.STATUS_EFFECT,
            new Identifier("catsplus", "cattify"),
            new CattifyEffect()
    );
}
