package cuteneko.catsplus;

import cuteneko.catsplus.data.*;
import cuteneko.catsplus.data.impl.CatServer;
import cuteneko.catsplus.data.impl.CatSpirit;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.data.impl.CatBag;
import cuteneko.catsplus.utility.Constants;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import java.util.Objects;

public class CatsPlusData {
    @ExpectPlatform
    public static ICatPlayer getCatPlayer(PlayerEntity player) {
        throw new UnsupportedOperationException();
    }

    @ExpectPlatform
    public static IGeniusCat getGeniusCat(CatEntity cat) {
        throw new UnsupportedOperationException();
    }

    public static ICatBag getCatBag(ItemStack bag) {
        if (!bag.isOf(ModItems.CAT_BAG.get())) {
            throw new IllegalArgumentException();
        }

        return new CatBag(bag);
    }

    public static ICatSpirit getCatSpirit(ItemStack spirit) {
        if (!spirit.isOf(ModItems.CAT_SPIRIT.get())) {
            throw new IllegalArgumentException();
        }

        return new CatSpirit(spirit);
    }

    public static ICatServer getCatServer(MinecraftServer server) {
        return Objects.requireNonNull(server.getWorld(World.OVERWORLD)).getPersistentStateManager()
                .getOrCreate(CatServer.TYPE, Constants.TAG_SERVER_HAS_CAT.toString());
    }
}
