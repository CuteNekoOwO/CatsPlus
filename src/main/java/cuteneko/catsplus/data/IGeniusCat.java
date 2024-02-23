package cuteneko.catsplus.data;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface IGeniusCat {
    boolean hasTotem();
    void setTotem(boolean totem);

    default boolean canRespawn() {
        return hasTotem() || getLives() > 0;
    }

    int getFavorability(PlayerEntity player);
    void setFavorability(int favorability, PlayerEntity player);

    default void addFavorability(int value, PlayerEntity player) {
        setFavorability(getFavorability(player) + value, player);
    }
    default void subFavorability(int value, PlayerEntity player) {
        setFavorability(getFavorability(player) - value, player);
    }

    int getLives();             // Todo: qyl27: Give it a purpose?
    void setLives(int lives);

    boolean isSongPlaying();
    BlockPos getSongSource();
    void songStartPlay(BlockPos source);
    void songStopPlay();
}
