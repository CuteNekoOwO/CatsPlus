package cuteneko.catsplus.impl;

import net.minecraft.entity.player.PlayerEntity;

public interface CatEntityMixinImpl {
    int getFavorability();
    void setFavorability(int favorability, PlayerEntity player);
    int getLives();
    void setLives(int lives);
    boolean isRespawnable();
    void setRespawnable(boolean respawnable);

    boolean isSongPlaying();

}
