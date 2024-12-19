package cuteneko.catsplus;

import cuteneko.catsplus.data.entity.GeniusCat;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.animal.Cat;

public class CatsPlusData {
    @ExpectPlatform
    public static GeniusCat getGeniusCat(Cat cat) {
        throw new AssertionError();
    }
}
