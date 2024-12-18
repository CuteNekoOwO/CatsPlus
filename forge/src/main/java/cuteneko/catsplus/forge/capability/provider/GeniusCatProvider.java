package cuteneko.catsplus.forge.capability.provider;

import cuteneko.catsplus.forge.capability.GeniusCatCapability;
import cuteneko.catsplus.forge.capability.ModCapabilities;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GeniusCatProvider implements ICapabilitySerializable<NbtCompound> {
    private final LazyOptional<GeniusCatCapability> optional = LazyOptional.of(GeniusCatCapability::new);

    public void invalidate() {
        optional.invalidate();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        if (capability == ModCapabilities.GENIUS_CAT) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public NbtCompound serializeNBT() {
        return optional.orElse(new GeniusCatCapability()).serializeNBT();
    }

    @Override
    public void deserializeNBT(NbtCompound nbt) {
        optional.orElse(new GeniusCatCapability()).deserializeNBT(nbt);
    }
}
