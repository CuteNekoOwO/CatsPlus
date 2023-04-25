package cuteneko.catsplus.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MyEffects {
    public static final StatusEffect CATTIFY =  MyEffects.register(
            new Identifier("catsplus", "cattify"),
            new CattifyEffect()
    );

    private static StatusEffect register(Identifier identifier, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, identifier, effect);
    }
}
