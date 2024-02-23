package cuteneko.catsplus.forge.capability.provider;

import cuteneko.catsplus.forge.capability.CatPlayerCapability;
import cuteneko.catsplus.forge.capability.GeniusCatCapability;
import cuteneko.catsplus.forge.capability.ModCapabilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CatPlayerProvider implements ICapabilitySerializable<NbtCompound> {
    private final PlayerEntity player;

    private final LazyOptional<CatPlayerCapability> optional;

    public CatPlayerProvider(PlayerEntity player) {
        this.player = player;
        this.optional = LazyOptional.of(() -> new CatPlayerCapability(player));
    }

    public void invalidate() {
        optional.invalidate();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        if (capability == ModCapabilities.CAT_PLAYER) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public NbtCompound serializeNBT() {
        return optional.orElse(new CatPlayerCapability(player)).serializeNBT();
    }

    @Override
    public void deserializeNBT(NbtCompound nbt) {
        optional.orElse(new CatPlayerCapability(player)).deserializeNBT(nbt);
    }
}
