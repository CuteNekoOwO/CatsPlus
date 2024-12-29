package cuteneko.catsplus.bridge;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;

public interface ICatBridge {
    boolean catsplus$isSoundPlaying();

    @NotNull
    BlockPos catsplus$getSoundSource();

    void catsplus$startSound(@NotNull BlockPos pos);

    void catsplus$stopSound();
}
