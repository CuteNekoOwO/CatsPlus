package cuteneko.catsplus.data.level;

import cuteneko.catsplus.utility.Constants;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LevelWithCats extends SavedData {

    // Todo: qyl27: DataFixerUpper.
    private static final Factory<LevelWithCats> FACTORY = new Factory<>(LevelWithCats::new, LevelWithCats::load, null);

    public static LevelWithCats getLevelWithCats(ServerLevel level) {
        return level.getDataStorage().get(FACTORY, "cats_plus");
    }

    private final Map<UUID, NonNullList<ItemStack>> catSpirits = new HashMap<>();

    private NonNullList<ItemStack> getCatSpirits(UUID uuid) {
        if (!catSpirits.containsKey(uuid)) {
            catSpirits.put(uuid, NonNullList.create());
            setDirty();
        }

        return catSpirits.get(uuid);
    }

    public void addCatSpirit(Player player, ItemStack catSpirit) {
        var list = getCatSpiritsByOwner(player);
        list.add(catSpirit);
        setDirty();
    }

    public NonNullList<ItemStack> getCatSpiritsByOwner(Player player) {
        return getCatSpirits(player.getUUID());
    }

    public void removeCatSpiritsByOwner(Player player) {
        catSpirits.remove(player.getUUID());
        setDirty();
    }

    public void clearCatSpirits() {
        catSpirits.clear();
        setDirty();
    }

    public LevelWithCats create() {
        return new LevelWithCats();
    }

    public static LevelWithCats load(CompoundTag tag, HolderLookup.Provider registries) {
        LevelWithCats data = new LevelWithCats();

        var spirits = tag.getList(Constants.TAG_SERVER_CAT_SPIRITS, Tag.TAG_COMPOUND);
        for (var spirit : spirits) {
            if (spirit instanceof CompoundTag compound) {
                var uuid = compound.getUUID(Constants.TAG_UUID);
                var value = NonNullList.<ItemStack>create();
                ContainerHelper.loadAllItems(compound.getCompound(Constants.TAG_VALUE), value, registries);
                data.catSpirits.put(uuid, value);
            }
        }

        return data;
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        var list = new ListTag();
        for (var entry : catSpirits.entrySet()) {
            var e = new CompoundTag();
            e.putUUID(Constants.TAG_UUID, entry.getKey());
            e.put(Constants.TAG_VALUE, ContainerHelper.saveAllItems(new CompoundTag(), entry.getValue(), registries));
        }
        tag.put(Constants.TAG_SERVER_CAT_SPIRITS, list);
        return tag;
    }
}
