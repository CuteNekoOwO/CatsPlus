package cuteneko.catsplus.effect;

import cuteneko.catsplus.CatsPlus;
import games.moegirl.sinocraft.sinocore.registry.IRegRef;
import games.moegirl.sinocraft.sinocore.registry.IRegistry;
import games.moegirl.sinocraft.sinocore.registry.RegistryManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {
    public static final IRegistry<MobEffect> REGISTRY = RegistryManager.obtain(CatsPlus.MODID, Registries.MOB_EFFECT);

    public static void register() {
        REGISTRY.register();
    }

    public static final IRegRef<MobEffect> CATTIFY = REGISTRY.register("cattify", CattifyEffect::new);
}
