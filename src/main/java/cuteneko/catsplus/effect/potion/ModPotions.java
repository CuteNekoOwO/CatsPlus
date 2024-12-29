package cuteneko.catsplus.effect.potion;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.effect.ModEffects;
import games.moegirl.sinocraft.sinocore.registry.IRegRef;
import games.moegirl.sinocraft.sinocore.registry.IRegistry;
import games.moegirl.sinocraft.sinocore.registry.RegistryManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {
    public static final IRegistry<Potion> REGISTRY = RegistryManager.create(CatsPlus.MODID, Registries.POTION);

    public static void register() {
        REGISTRY.register();
    }

    public static final IRegRef<Potion> CATTIFY = REGISTRY.register("cattify", () -> new Potion(new MobEffectInstance(ModEffects.CATTIFY.getHolder(), 1800)));
    public static final IRegRef<Potion> LONG_CATTIFY = REGISTRY.register("long_cattify", () -> new Potion("cattify", new MobEffectInstance(ModEffects.CATTIFY.getHolder(), 4800)));
}
