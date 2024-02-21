package cuteneko.catsplus.item.group;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.utility.Constants;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class ModItemGroups {

    public static final DeferredRegister<ItemGroup> GROUPS = DeferredRegister.create(CatsPlus.MODID, RegistryKeys.ITEM_GROUP);

    public static void register() {
        GROUPS.register();
    }

    public static final RegistrySupplier<ItemGroup> CATS_PLUS = GROUPS.register("catsplus_group", () -> CreativeTabRegistry.create(Text.translatable(Constants.MESSAGE_CATS_GROUP_TITLE), () -> new ItemStack(ModItems.CAT_BAG)));
}
