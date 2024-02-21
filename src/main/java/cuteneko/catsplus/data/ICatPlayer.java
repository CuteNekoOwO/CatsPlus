package cuteneko.catsplus.data;

import net.minecraft.entity.passive.CatEntity;

public interface ICatPlayer {
    CatEntity getCatEntity();
    void setCatEntity(CatEntity cat);

    boolean isCat();
    void setCat(boolean cat);
}
