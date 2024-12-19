package cuteneko.catsplus.fabric;

import cuteneko.catsplus.data.entity.GeniusCat;
import cuteneko.catsplus.fabric.mixins.impl.ICatEntityMixin;
import net.minecraft.world.entity.animal.Cat;

public class CatsPlusDataImpl {
    public static GeniusCat getGeniusCat(Cat cat) {
        return ((ICatEntityMixin) cat).catsplus$getGeniusCat();
    }
}
