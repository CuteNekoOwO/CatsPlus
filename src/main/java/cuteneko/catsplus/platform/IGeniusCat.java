package cuteneko.catsplus.platform;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface IGeniusCat {
    boolean hasTotem();
    void setTotem(boolean totem);

    boolean canRespawn();
    void setCanRespawn(boolean canRespawn);

    int getFavorability(PlayerEntity player);
    void setFavorability(int favorability, PlayerEntity player);
    void addFavorability(int value, PlayerEntity player);
    void subFavorability(int value, PlayerEntity player);

    int getLives();
    void setLives(int lives);

    boolean isSongPlaying();
    BlockPos getSongSource();
    void songStartPlay(BlockPos source);
    void songStopPlay();
}
