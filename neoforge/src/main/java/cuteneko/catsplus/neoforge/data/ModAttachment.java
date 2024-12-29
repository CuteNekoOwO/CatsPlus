package cuteneko.catsplus.neoforge.data;

import cuteneko.catsplus.CatsPlus;
import net.minecraft.world.entity.animal.Cat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachment {
    private static final DeferredRegister<AttachmentType<?>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, CatsPlus.MODID);

    public static final Supplier<AttachmentType<GeniusCatNeoForge>> GENIUS_CAT = REGISTRY.register("genius_cat", () -> AttachmentType
            .serializable(holder -> holder instanceof Cat cat ? new GeniusCatNeoForge(cat) : null)
            .build());

    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }
}
