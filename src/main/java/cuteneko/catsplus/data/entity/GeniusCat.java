package cuteneko.catsplus.data.entity;

import cuteneko.catsplus.data.ICompoundSerializable;
import cuteneko.catsplus.utility.ModConstants;
import cuteneko.catsplus.utility.TagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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

    // <editor-fold desc="Dancing">

    public static final EntityDataAccessor<Boolean> SOUND_PLAYING = SynchedEntityData.defineId(Cat.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<BlockPos> SOUND_SOURCE = SynchedEntityData.defineId(Cat.class, EntityDataSerializers.BLOCK_POS);

    public void setSoundPlaying(BlockPos source) {
        if (source != null) {
            cat.getEntityData().set(SOUND_PLAYING, true);
            cat.getEntityData().set(SOUND_SOURCE, source);
        } else {
            cat.getEntityData().set(SOUND_PLAYING, false);
        }
    }

    public void setSoundStopped() {
        cat.getEntityData().set(SOUND_PLAYING, false);
    }

    public boolean isSoundPlaying() {
        return cat.getEntityData().get(SOUND_PLAYING);
    }

    public BlockPos getSoundSource() {
        return cat.getEntityData().get(SOUND_SOURCE);
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

        if (isSoundPlaying()) {
            tag.put(ModConstants.TAG_GENIUS_CAT_SOUND_SOURCE, TagHelper.saveBlockPos(getSoundSource()));
        } else {
            tag.remove(ModConstants.TAG_GENIUS_CAT_SOUND_SOURCE);
        }

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

        if (tag.contains(ModConstants.TAG_GENIUS_CAT_SOUND_SOURCE)) {
            var pos = TagHelper.loadBlockPos(tag.getCompound(ModConstants.TAG_GENIUS_CAT_SOUND_SOURCE));
            if (pos != null) {
                setSoundPlaying(pos);
            }
        }
    }
}
