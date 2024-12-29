package cuteneko.catsplus.item.group;

import cuteneko.catsplus.CatsPlus;
import games.moegirl.sinocraft.sinocore.registry.ITabRegistry;
import games.moegirl.sinocraft.sinocore.registry.RegistryManager;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class ModItemGroups {

    private static final ITabRegistry REGISTRY = RegistryManager.obtainTab(CatsPlus.MODID);

    public static void register() {
        REGISTRY.register();
    }

    public static final ResourceKey<CreativeModeTab> CATS_PLUS = REGISTRY.register("catsplus_group");
    //, () -> new CreativeModeTab.Builder(Component.translatable(ModConstants.MESSAGE_CATS_GROUP_TITLE), () -> new ItemStack(ModItems.CAT_BAG)));
}
