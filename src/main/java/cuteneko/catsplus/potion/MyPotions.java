package cuteneko.catsplus.potion;

import cuteneko.catsplus.effect.MyEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MyPotions {
    public static final Potion CATTIFY = MyPotions.register(
            new Identifier("catsplus", "cattify"),
                new Potion(new StatusEffectInstance(MyEffects.CATTIFY, 1800))
    );
    public static final Potion LONG_CATTIFY = MyPotions.register(
            new Identifier("catsplus", "long_cattify"),
            new Potion("cattify", new StatusEffectInstance(MyEffects.CATTIFY, 4800))
    );

    private static Potion register(Identifier identifier, Potion potion) {
        return Registry.register(Registries.POTION, identifier, potion);
    }

    public static void registerRecipe() {
        BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, Items.COD, CATTIFY);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, Items.SALMON, CATTIFY);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, Items.COOKED_COD, CATTIFY);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, Items.COOKED_SALMON, CATTIFY);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, Items.TROPICAL_FISH, CATTIFY);
        BrewingRecipeRegistry.registerPotionRecipe(CATTIFY, Items.REDSTONE, LONG_CATTIFY);
    }
}
