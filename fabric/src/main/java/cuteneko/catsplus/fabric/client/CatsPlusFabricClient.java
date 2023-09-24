package cuteneko.catsplus.fabric.client;

import cuteneko.catsplus.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class CatsPlusFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelPredicateProviderRegistry.register(
                ModItems.CAT_BAG,
                new Identifier("catsplus", "cat"),
                (itemStack, clientWorld, livingEntity, index) -> {
                    if(!itemStack.hasNbt() || !Objects.requireNonNull(itemStack.getNbt()).contains("Cat")) {
                        return 0F;
                    }

                    var nbt = itemStack.getNbt().getCompound("Cat");
                    return switch (nbt.getString("variant")) {
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
                        default -> 1F;
                    };
                });

        ColorProviderRegistry.ITEM.register(
                ((stack, tintIndex) -> tintIndex > 0 ? -1 :  ((DyeableItem) stack.getItem()).getColor(stack)),
                ModItems.CAT_BAG
        );
    }
}
