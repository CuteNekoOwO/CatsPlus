package cuteneko.catsplus.effect;

import cuteneko.catsplus.CatsPlus;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(CatsPlus.MODID, Registries.MOB_EFFECT);

    public static void register() {
        REGISTRY.register();
    }

    public static final RegistrySupplier<MobEffect> CATTIFY = REGISTRY.register("cattify", CattifyEffect::new);
}
