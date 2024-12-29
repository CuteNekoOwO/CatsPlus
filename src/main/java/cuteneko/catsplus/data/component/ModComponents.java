package cuteneko.catsplus.data.component;

import cuteneko.catsplus.CatsPlus;
import games.moegirl.sinocraft.sinocore.registry.IRegRef;
import games.moegirl.sinocraft.sinocore.registry.IRegistry;
import games.moegirl.sinocraft.sinocore.registry.RegistryManager;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;

public class ModComponents {
    private static final IRegistry<DataComponentType<?>> REGISTRY = RegistryManager.obtain(CatsPlus.MODID, Registries.DATA_COMPONENT_TYPE);

    public static void register() {
        REGISTRY.register();
    }

    public static final IRegRef<DataComponentType<CatContainer>> CAT_CONTAINER = REGISTRY.register("cat_container",
            () -> DataComponentType.<CatContainer>builder()
                    .persistent(CatContainer.CODEC)
                    .networkSynchronized(CatContainer.STREAM_CODEC)
                    .build());

    public static final IRegRef<DataComponentType<CatSpirit>> CAT_SPIRIT = REGISTRY.register("cat_spirit",
            () -> DataComponentType.<CatSpirit>builder()
                    .persistent(CatSpirit.CODEC)
                    .networkSynchronized(CatSpirit.STREAM_CODEC)
                    .build());
}
