package cuteneko.catsplus;

import cuteneko.catsplus.block.ModBlocks;
import cuteneko.catsplus.effect.ModEffects;
import cuteneko.catsplus.effect.potion.ModPotions;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.item.group.ModItemGroups;
import cuteneko.catsplus.listener.CatSpiritListener;
import cuteneko.catsplus.listener.ModListeners;
import cuteneko.catsplus.utility.Constants;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class CatsPlus {
    public static final String MODID = "catsplus";

    private static CatsPlus INSTANCE;

    public CatsPlus() {
        INSTANCE = this;
    }

    public static CatsPlus getInstance() {
        return INSTANCE;
    }

    public void init() {
        ModItemGroups.register();
        ModBlocks.register();
        ModItems.register();
        ModEffects.register();
        ModPotions.register();
        ModListeners.register();
    }

    public void initClient() {
        ItemPropertiesRegistry.register(ModItems.CAT_BAG.get(), new Identifier(CatsPlus.MODID, "cat"),
                (stack, clientLevel, livingEntity, i) -> {
                    if(!stack.hasNbt()
                            || stack.getNbt() == null
                            || !Objects.requireNonNull(stack.getNbt()).contains(Constants.TAG_CAT_CONTAINER)) {
                        return 0F;
                    }

                    var nbt = stack.getNbt().getCompound(Constants.TAG_CAT_CONTAINER);
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
    }
}
