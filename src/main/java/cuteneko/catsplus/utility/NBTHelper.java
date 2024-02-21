package cuteneko.catsplus.utility;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class NBTHelper {
    public static NbtCompound putBlockPos(NbtCompound compound, BlockPos pos) {
        if (pos != null) {
            compound.putInt(Constants.TAG_X, pos.getX());
            compound.putInt(Constants.TAG_Y, pos.getY());
            compound.putInt(Constants.TAG_Z, pos.getZ());
        }
        return compound;
    }

    public static BlockPos getBlockPos(NbtCompound compound) {
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
