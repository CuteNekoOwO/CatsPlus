package cuteneko.catsplus.mixins.bridge.dancing;

import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public interface IMusicianCat {
    void catsplus$setSoundSource(@Nullable BlockPos pos);

    @Nullable
    BlockPos catsplus$getSoundSource();
}
