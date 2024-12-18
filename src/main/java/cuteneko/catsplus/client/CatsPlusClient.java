package cuteneko.catsplus.client;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.utility.ComponentHelper;
import dev.architectury.registry.item.ItemPropertiesRegistry;

public class CatsPlusClient {

    public static void initClient() {
        ItemPropertiesRegistry.register(ModItems.CAT_BAG.get(), CatsPlus.modLoc("cat"),
                (stack, clientLevel, livingEntity, i) -> {
                    var catContainer = ComponentHelper.getCatContainer(stack);
                    if (catContainer == null) {
                        return 0;
                    }

                    return switch (catContainer.variant()) {
                        case "minecraft:tabby" -> 0.05F;
                        case "minecraft:black" -> 0.1F;
                        case "minecraft:red" -> 0.15F;
                        case "minecraft:siamese" -> 0.2F;
                        case "minecraft:british_shorthair" -> 0.25F;
                        case "minecraft:calico" -> 0.3F;
                        case "minecraft:persian" -> 0.35F;
                        case "minecraft:ragdoll" -> 0.4F;
                        case "minecraft:white" -> 0.45F;
                        case "minecraft:jellie" -> 0.5F;
                        case "minecraft:all_black" -> 0.55F;
                        default -> 0F;
                    };
                });
    }
}
