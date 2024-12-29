package cuteneko.catsplus.data.entity;

import cuteneko.catsplus.data.ICompoundSerializable;
import cuteneko.catsplus.bridge.ICatBridge;
import cuteneko.catsplus.utility.ModConstants;
import cuteneko.catsplus.utility.TagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GeniusCat implements ICompoundSerializable {

    private final Cat cat;

    public GeniusCat(Cat cat) {
        this.cat = cat;
    }

    // <editor-fold desc="Intimacies">

    // Cat's intimacies with player, [-100, 100]
    private final Map<UUID, Integer> intimacies = new HashMap<>();

    public int getIntimacyWith(Player player) {
        var uuid = player.getUUID();
        if (!intimacies.containsKey(uuid)) {
            intimacies.put(uuid, 0);
        }

        return intimacies.get(uuid);
    }

    public void setIntimacyWith(Player player, int value) {
        intimacies.put(player.getUUID(), value);
    }

    public void addIntimacyWith(Player player, int value) {
        setIntimacyWith(player, getIntimacyWith(player) + value);
    }

    public void subIntimacyWith(Player player, int value) {
        setIntimacyWith(player, getIntimacyWith(player) - value);
    }

    // </editor-fold>

    // <editor-fold desc="Totem">

    private boolean undyingTotem = false;

    public boolean hasTotem() {
        return undyingTotem;
    }

    public void setTotem(boolean totem) {
        this.undyingTotem = totem;
    }

    // </editor-fold>

    @Override
    public @NotNull CompoundTag serializeTag(HolderLookup.Provider registries) {
        var tag = new CompoundTag();
        tag.putBoolean(ModConstants.TAG_GENIUS_CAT_HAS_TOTEM, hasTotem());

        var intimaciesTag = new CompoundTag();
        for (var entry : this.intimacies.entrySet()) {
            intimaciesTag.putInt(entry.getKey().toString(), entry.getValue());
        }
        tag.put(ModConstants.TAG_GENIUS_CAT_INTIMACIES, intimaciesTag);
        return tag;
    }

    @Override
    public void deserializeTag(@NotNull CompoundTag tag, HolderLookup.Provider registries) {
        if (tag.contains(ModConstants.TAG_GENIUS_CAT_HAS_TOTEM)) {
            setTotem(tag.getBoolean(ModConstants.TAG_GENIUS_CAT_HAS_TOTEM));
        }

        if (tag.contains(ModConstants.TAG_GENIUS_CAT_INTIMACIES)) {
            var intimaciesTag = tag.getCompound(ModConstants.TAG_GENIUS_CAT_INTIMACIES);
            for (var e : intimaciesTag.getAllKeys()) {
                var uuid = UUID.fromString(e);
                var v = intimaciesTag.getInt(e);
                this.intimacies.put(uuid, v);
            }
        }
    }
}
