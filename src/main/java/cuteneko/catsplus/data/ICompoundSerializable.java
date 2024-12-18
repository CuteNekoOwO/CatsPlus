package cuteneko.catsplus.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

public interface ICompoundSerializable {
    @NotNull CompoundTag serializeNBT(HolderLookup.Provider registries);

    void deserializeNBT(@NotNull CompoundTag tag, HolderLookup.Provider registries);
}
