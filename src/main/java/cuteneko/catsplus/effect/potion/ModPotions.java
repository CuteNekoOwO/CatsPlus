package cuteneko.catsplus.effect.potion;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.effect.ModEffects;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.RegistryKeys;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(CatsPlus.MODID, RegistryKeys.POTION);

    public static void register() {
        POTIONS.register();
    }

    public static final RegistrySupplier<Potion> CATTIFY = POTIONS.register("cattify", () -> new Potion(new StatusEffectInstance(ModEffects.CATTIFY.get(), 1800)));
    public static final RegistrySupplier<Potion> LONG_CATTIFY = POTIONS.register("long_cattify", () -> new Potion("cattify", new StatusEffectInstance(ModEffects.CATTIFY.get(), 4800)));
}
