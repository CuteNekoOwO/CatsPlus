package cuteneko.catsplus.fabric.registry;

import cuteneko.catsplus.effect.potion.ModPotions;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PotionRegistry {
    private static Potion register(Identifier identifier, Potion potion) {
        return Registry.register(Registries.POTION, identifier, potion);
    }

    public static void register() {
        PotionRegistry.register(ModPotions.ID_CATTIFY, ModPotions.CATTIFY);
        PotionRegistry.register(ModPotions.ID_LONG_CATTIFY, ModPotions.LONG_CATTIFY);

        registerRecipe();
    }

    private static void registerRecipe() {
        BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, Items.COD, ModPotions.CATTIFY);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, Items.SALMON, ModPotions.CATTIFY);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, Items.COOKED_COD, ModPotions.CATTIFY);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, Items.COOKED_SALMON, ModPotions.CATTIFY);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, Items.TROPICAL_FISH, ModPotions.CATTIFY);
        BrewingRecipeRegistry.registerPotionRecipe(ModPotions.CATTIFY, Items.REDSTONE, ModPotions.LONG_CATTIFY);
    }
}
