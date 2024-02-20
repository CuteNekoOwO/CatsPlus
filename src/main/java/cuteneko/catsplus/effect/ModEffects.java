package cuteneko.catsplus.effect;

import cuteneko.catsplus.CatsPlus;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKeys;

public class ModEffects {
    public static final DeferredRegister<StatusEffect> EFFECTS = DeferredRegister.create(CatsPlus.MODID, RegistryKeys.STATUS_EFFECT);

    public static void register() {
        EFFECTS.register();
    }

    public static final RegistrySupplier<StatusEffect> CATTIFY = EFFECTS.register("cattify", CattifyEffect::new);
}
