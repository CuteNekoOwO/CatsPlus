package cuteneko.catsplus.neoforge;

import cuteneko.catsplus.data.entity.GeniusCat;
import cuteneko.catsplus.neoforge.data.ModAttachment;
import net.minecraft.world.entity.animal.Cat;

public class CatsPlusDataImpl {
    public static GeniusCat getGeniusCat(Cat cat) {
        return cat.getData(ModAttachment.GENIUS_CAT);
    }
}
