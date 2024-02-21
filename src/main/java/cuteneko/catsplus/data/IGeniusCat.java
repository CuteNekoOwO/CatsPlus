package cuteneko.catsplus.data;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface IGeniusCat {
    boolean hasTotem();
    void setTotem(boolean totem);

    boolean canRespawn();

    int getFavorability(PlayerEntity player);
    void setFavorability(int favorability, PlayerEntity player);
    void addFavorability(int value, PlayerEntity player);
    void subFavorability(int value, PlayerEntity player);

    int getLives();             // Todo: qyl27: Give it a purpose?
    void setLives(int lives);

    boolean isSongPlaying();
    BlockPos getSongSource();
    void songStartPlay(BlockPos source);
    void songStopPlay();
}
