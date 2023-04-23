package cuteneko.catsplus.impl;

import net.minecraft.entity.passive.CatEntity;

public interface PlayerEntityMixinImpl {
    CatEntity getCat();
    boolean isCat();
}
