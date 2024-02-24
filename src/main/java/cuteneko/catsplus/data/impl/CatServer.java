package cuteneko.catsplus.data.impl;

import com.google.common.collect.ImmutableMap;
import cuteneko.catsplus.data.ICatServer;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CatServer extends PersistentState implements ICatServer {

    // Todo: qyl27: DataFixerUpper.
    public static final Type<CatServer> TYPE = new Type<>(CatServer::new, CatServer::loadFromNbt, null);

    private final Map<UUID, DefaultedList<ItemStack>> catSpirits = new HashMap<>();

    @Override
    public Map<UUID, DefaultedList<ItemStack>> getCatSpirits() {
        return ImmutableMap.<UUID, DefaultedList<ItemStack>>builder().putAll(catSpirits).build();
    }

    @Override
    public DefaultedList<ItemStack> getCatSpiritsByOwner(UUID uuid) {
        if (!catSpirits.containsKey(uuid)) {
            catSpirits.put(uuid, DefaultedList.of());
        }

        return catSpirits.get(uuid);
    }

    @Override
    public void addCatSpirit(UUID uuid, ItemStack catSpirit) {
        if (uuid == null) {
            return;
        }

        var list = getCatSpiritsByOwner(uuid);
        list.add(catSpirit);
    }

    @Override
    public void clearCatSpirits() {
        catSpirits.clear();
    }

    @Override
    public void clearCatSpiritsByOwner(UUID uuid) {
        getCatSpiritsByOwner(uuid).clear();
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        var list = new NbtList();
        for (var entry : getCatSpirits().entrySet()) {
            var e = new NbtCompound();
            e.putUuid(Constants.TAG_UUID, entry.getKey());
            e.put(Constants.TAG_VALUE, Inventories.writeNbt(new NbtCompound(), entry.getValue()));
        }
        nbt.put(Constants.TAG_SERVER_CAT_SPIRITS, list);
        return nbt;
    }

    public static CatServer loadFromNbt(NbtCompound nbt) {
        var result = new CatServer();

        var spirits = nbt.getList(Constants.TAG_SERVER_CAT_SPIRITS, NbtElement.COMPOUND_TYPE);
        for (var spirit : spirits) {
            if (spirit instanceof NbtCompound compound) {
                var uuid = compound.getUuid(Constants.TAG_UUID);
                var value = DefaultedList.<ItemStack>of();
                Inventories.readNbt(compound.getCompound(Constants.TAG_VALUE), value);
                result.catSpirits.put(uuid, value);
            }
        }

        return result;
    }
}
