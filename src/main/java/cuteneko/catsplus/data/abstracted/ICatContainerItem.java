package cuteneko.catsplus.data.abstracted;

import net.minecraft.entity.passive.CatEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public interface ICatContainerItem {
    boolean hasCat();
    CatEntity getCat(World world);
    void setCat(CatEntity cat);
    void clearCat();

    boolean hasCustomCatName();
    Text getCustomCatName();
}
