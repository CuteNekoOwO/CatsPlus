package cuteneko.catsplus.fabric.mixins.impl;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface ICatEntityMixin {
    boolean catsplus$hasTotem();
    void catsplus$setTotem(boolean totem);

    int catsplus$getFavorability(PlayerEntity player);
    void catsplus$setFavorability(int favorability, PlayerEntity player);

    int catsplus$getLives();
    void catsplus$setLives(int lives);

    boolean catsplus$canRespawn();
    void catsplus$setCanRespawn(boolean canRespawn);

    boolean catsplus$isSongPlaying();
    void catsplus$setSongPlaying(boolean playing);
    BlockPos catsplus$getSongSource();
    void catsplus$setSongSource(BlockPos source);
}
