package cuteneko.catsplus.effect.potion;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.effect.ModEffects;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {
    public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(CatsPlus.MODID, Registries.POTION);

    public static void register() {
        REGISTRY.register();
    }

    public static final RegistrySupplier<Potion> CATTIFY = REGISTRY.register("cattify", () -> new Potion(new MobEffectInstance(ModEffects.CATTIFY, 1800)));
    public static final RegistrySupplier<Potion> LONG_CATTIFY = REGISTRY.register("long_cattify", () -> new Potion("cattify", new MobEffectInstance(ModEffects.CATTIFY, 4800)));
}
