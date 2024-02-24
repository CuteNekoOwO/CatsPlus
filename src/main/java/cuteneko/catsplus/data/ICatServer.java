package cuteneko.catsplus.data;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.util.Map;
import java.util.UUID;

public interface ICatServer {
    Map<UUID, DefaultedList<ItemStack>> getCatSpirits();

    DefaultedList<ItemStack> getCatSpiritsByOwner(UUID uuid);

    default DefaultedList<ItemStack> getCatSpiritsByOwner(PlayerEntity player) {
        return getCatSpiritsByOwner(player.getUuid());
    }

    void addCatSpirit(UUID uuid, ItemStack catSpirit);

    void clearCatSpirits();

    void clearCatSpiritsByOwner(UUID uuid);

    default void clearCatSpiritsByOwner(PlayerEntity player) {
        clearCatSpiritsByOwner(player.getUuid());
    }
}
