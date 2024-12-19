package cuteneko.catsplus.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public class TagHelper {
    public static CompoundTag saveBlockPos(BlockPos pos) {
        var compound = new CompoundTag();
        if (pos != null) {
            compound.putInt(ModConstants.TAG_X, pos.getX());
            compound.putInt(ModConstants.TAG_Y, pos.getY());
            compound.putInt(ModConstants.TAG_Z, pos.getZ());
        }
        return compound;
    }

    public static BlockPos loadBlockPos(CompoundTag compound) {
        if (compound.contains(ModConstants.TAG_X)
                && compound.contains(ModConstants.TAG_Y)
                && compound.contains(ModConstants.TAG_Z)) {
            var x = compound.getInt(ModConstants.TAG_X);
            var y = compound.getInt(ModConstants.TAG_Y);
            var z = compound.getInt(ModConstants.TAG_Z);
            return new BlockPos(x, y, z);
        }
        return null;
    }
}
