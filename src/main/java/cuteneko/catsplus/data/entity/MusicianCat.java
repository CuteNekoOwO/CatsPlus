package cuteneko.catsplus.data.entity;

import cuteneko.catsplus.data.ICompoundSerializable;
import cuteneko.catsplus.utility.TagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MusicianCat implements ICompoundSerializable {
    @Nullable


    public void setSoundSource(@Nullable BlockPos pos) {
        this.soundSource = pos;
    }

    public @Nullable BlockPos getSoundSource() {
        return soundSource;
    }

    @Override
    public @NotNull CompoundTag serializeNBT(HolderLookup.Provider registries) {
        var tag = new CompoundTag();
        tag.put("soundSource", TagHelper.saveBlockPos(getSoundSource()));
        return tag;
    }

    @Override
    public void deserializeNBT(@NotNull CompoundTag tag, HolderLookup.Provider registries) {
        setSoundSource(TagHelper.loadBlockPos(tag));
    }
}
