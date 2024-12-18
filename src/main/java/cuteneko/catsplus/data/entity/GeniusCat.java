package cuteneko.catsplus.data.entity;

import cuteneko.catsplus.data.ICompoundSerializable;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GeniusCat implements ICompoundSerializable {
    private boolean undyingTotem = false;

    public boolean hasTotem() {
        return undyingTotem;
    }

    public void setTotem(boolean totem) {
        this.undyingTotem = totem;
    }

    // Cat's intimacies with player, [-100, 100]
    private final Map<UUID, Integer> intimacies = new HashMap<>();

    public Integer getIntimacyWith(Player player) {
        return intimacies.putIfAbsent(player.getUUID(), 0);
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

    @Override
    public @NotNull CompoundTag serializeNBT(HolderLookup.Provider registries) {
        var tag = new CompoundTag();
        tag.putBoolean("undyingTotem", hasTotem());

        var intimaciesTag = new CompoundTag();
        for (var entry : this.intimacies.entrySet()) {
            intimaciesTag.putInt(entry.getKey().toString(), entry.getValue());
        }
        tag.put("intimacies", intimaciesTag);

        return tag;
    }

    @Override
    public void deserializeNBT(@NotNull CompoundTag tag, HolderLookup.Provider registries) {
        if (tag.contains("undyingTotem")) {
            setTotem(tag.getBoolean("undyingTotem"));
        }

        if (tag.contains("intimacies")) {
            var intimaciesTag = tag.getCompound("intimaciesTag");
            for (var e : intimaciesTag.getAllKeys()) {
                var uuid = UUID.fromString(e);
                var v = intimaciesTag.getInt(e);
                this.intimacies.put(uuid, v);
            }
        }
    }
}
