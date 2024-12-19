package cuteneko.catsplus.neoforge.data;

import cuteneko.catsplus.data.entity.GeniusCat;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.Cat;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

public class GeniusCatNeoForge extends GeniusCat implements INBTSerializable<CompoundTag> {
    public GeniusCatNeoForge(Cat cat) {
        super(cat);
    }

    @Override
    public @NotNull CompoundTag serializeNBT(HolderLookup.@NotNull Provider registries) {
        return super.serializeTag(registries);
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider registries, @NotNull CompoundTag tag) {
        super.deserializeTag(tag, registries);
    }
}
