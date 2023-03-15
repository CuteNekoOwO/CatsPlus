package cuteneko.catsplus.accessor;

import net.minecraft.entity.player.PlayerEntity;

public interface CatEntityMixinAccessor {
    int getFavorability();
    void setFavorability(int favorability, PlayerEntity player);
    int getLives();
    void setLives(int lives);
    boolean getRespawnable();
    void setRespawnable(boolean respawnable);

    boolean isSongPlaying();
}
