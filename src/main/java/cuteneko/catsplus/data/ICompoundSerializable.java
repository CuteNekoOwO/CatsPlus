package cuteneko.catsplus.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

public interface ICompoundSerializable {
    @NotNull CompoundTag serializeTag(HolderLookup.Provider registries);

    void deserializeTag(@NotNull CompoundTag tag, HolderLookup.Provider registries);
}
