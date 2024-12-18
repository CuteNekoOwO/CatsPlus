package cuteneko.catsplus.forge.capability;

import cuteneko.catsplus.data.IGeniusCat;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GeniusCatCapability implements IGeniusCat, INBTSerializable<NbtCompound> {

    private int lives = 0;
    private boolean totem = false;

    private final Map<UUID, Integer> favorability = new HashMap<>();

    @Override
    public boolean hasTotem() {
        return totem;
    }

    @Override
    public void setTotem(boolean totem) {
        this.totem = totem;
    }

    @Override
    public int getFavorability(PlayerEntity player) {
        if (!favorability.containsKey(player.getUuid())) {
            favorability.put(player.getUuid(), 0);
        }

        return favorability.get(player.getUuid());
    }

    @Override
    public void setFavorability(int favorability, PlayerEntity player) {
        this.favorability.put(player.getUuid(), favorability);
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public NbtCompound serializeNBT() {
        var tag = new NbtCompound();

        tag.putInt(Constants.TAG_GENIUS_CAT_LIVES, lives);
        tag.putBoolean(Constants.TAG_GENIUS_CAT_TOTEM, totem);

        var favorability = new NbtList();
        for (var entry : this.favorability.entrySet()) {
            var f = new NbtCompound();
            f.putUuid(Constants.TAG_UUID, entry.getKey());
            f.putInt(Constants.TAG_VALUE, entry.getValue());
            favorability.add(f);
        }
        tag.put(Constants.TAG_GENIUS_CAT_FAVORABILITY, favorability);

        return tag;
    }

    @Override
    public void deserializeNBT(NbtCompound tag) {
        lives = tag.getInt(Constants.TAG_GENIUS_CAT_LIVES);
        totem = tag.getBoolean(Constants.TAG_GENIUS_CAT_TOTEM);

        var favorability = tag.getList(Constants.TAG_GENIUS_CAT_FAVORABILITY, NbtElement.LIST_TYPE);
        for (var entry : favorability) {
            if (entry instanceof NbtCompound e
                    && e.contains(Constants.TAG_UUID)
                    && e.contains(Constants.TAG_VALUE)) {
                var uuid = e.getUuid(Constants.TAG_UUID);
                var value = e.getInt(Constants.TAG_VALUE);

                this.favorability.put(uuid, value);
            }
        }
    }
}
