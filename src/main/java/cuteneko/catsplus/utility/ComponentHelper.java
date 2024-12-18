package cuteneko.catsplus.utility;

import cuteneko.catsplus.data.component.CatContainer;
import cuteneko.catsplus.data.component.ModComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class ComponentHelper {
    public static CatContainer getCatContainer(ItemStack stack) {
        return stack.get(ModComponents.CAT_CONTAINER.get());
    }

    public static void setCatContainer(ItemStack stack, CatContainer catContainer) {
        stack.set(ModComponents.CAT_CONTAINER.get(), catContainer);
    }

    public static void removeCatContainer(ItemStack stack) {
        stack.remove(ModComponents.CAT_CONTAINER.get());
    }

    public static Component getCustomName(ItemStack stack) {
        if (stack.has(DataComponents.CUSTOM_NAME)) {
            return stack.get(DataComponents.CUSTOM_NAME);
        }

        return null;
    }

    public static void setCustomName(ItemStack stack, Component name) {
        stack.set(DataComponents.CUSTOM_NAME, name);
    }

    public static void removeCustomName(ItemStack stack) {
        stack.remove(DataComponents.CUSTOM_NAME);
    }
}
