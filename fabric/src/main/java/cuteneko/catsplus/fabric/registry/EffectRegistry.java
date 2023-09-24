package cuteneko.catsplus.fabric.registry;

import cuteneko.catsplus.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EffectRegistry {
    private static void register(Identifier identifier, StatusEffect effect) {
        Registry.register(Registries.STATUS_EFFECT, identifier, effect);
    }

    public static void register() {
        register(ModEffects.ID_CATTIFY, ModEffects.CATTIFY);
    }
}
