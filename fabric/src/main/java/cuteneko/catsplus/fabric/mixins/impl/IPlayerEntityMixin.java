package cuteneko.catsplus.fabric.mixins.impl;

import net.minecraft.entity.passive.CatEntity;

public interface IPlayerEntityMixin {
    CatEntity catsplus$getCatEntity();
    void catsplus$setCatEntity(CatEntity cat);

    boolean catsplus$isCat();
    void catsplus$setCat(boolean cat);
}
