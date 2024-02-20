package cuteneko.catsplus.platform;

import net.minecraft.entity.passive.CatEntity;

public interface ICatPlayer {
    CatEntity getCatEntity();
    void setCatEntity(CatEntity cat);

    boolean isCat();
    void setCat(boolean cat);
}
