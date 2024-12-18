package cuteneko.catsplus.item.group;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.utility.Constants;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {

    private static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(CatsPlus.MODID, Registries.CREATIVE_MODE_TAB);

    public static void register() {
        REGISTRY.register();
    }

    public static final RegistrySupplier<CreativeModeTab> CATS_PLUS = REGISTRY.register("catsplus_group", () -> CreativeTabRegistry.create(Component.translatable(Constants.MESSAGE_CATS_GROUP_TITLE), () -> new ItemStack(ModItems.CAT_BAG)));
}
