package cuteneko.catsplus.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MyEffects {
    public static final StatusEffect CATTIFY = new CattifyEffect();

    private static void register(Identifier identifier, StatusEffect effect) {
        Registry.register(Registries.STATUS_EFFECT, identifier, effect);
    }

    public static void register() {
        register(new Identifier("catsplus", "cattify"), CATTIFY);
    }
}
