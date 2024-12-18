package cuteneko.catsplus.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public class TagHelper {
    public static CompoundTag saveBlockPos(BlockPos pos) {
        var compound = new CompoundTag();
        if (pos != null) {
            compound.putInt(Constants.TAG_X, pos.getX());
            compound.putInt(Constants.TAG_Y, pos.getY());
            compound.putInt(Constants.TAG_Z, pos.getZ());
        }
        return compound;
    }

    public static BlockPos loadBlockPos(CompoundTag compound) {
        if (compound.contains(Constants.TAG_X)
                && compound.contains(Constants.TAG_Y)
                && compound.contains(Constants.TAG_Z)) {
            var x = compound.getInt(Constants.TAG_X);
            var y = compound.getInt(Constants.TAG_Y);
            var z = compound.getInt(Constants.TAG_Z);
            return new BlockPos(x, y, z);
        }
        return null;
    }
}
