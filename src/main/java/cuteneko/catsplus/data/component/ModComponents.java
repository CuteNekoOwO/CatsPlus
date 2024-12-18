package cuteneko.catsplus.data.component;

import cuteneko.catsplus.CatsPlus;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;

public class ModComponents {
    private static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(CatsPlus.MODID, Registries.DATA_COMPONENT_TYPE);

    public static void register() {
        REGISTRY.register();
    }

    public static final DeferredSupplier<DataComponentType<CatContainer>> CAT_CONTAINER = REGISTRY.register("cat_container",
            () -> DataComponentType.<CatContainer>builder()
                    .persistent(CatContainer.CODEC)
                    .networkSynchronized(CatContainer.STREAM_CODEC)
                    .build());
}
