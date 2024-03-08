package cuteneko.catsplus.fabric.data;

import cuteneko.catsplus.fabric.mixins.impl.ICatEntityMixin;
import cuteneko.catsplus.data.IGeniusCat;
import cuteneko.catsplus.mixins.bridge.dancing.IMusicianCat;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class GeniusCatFabric implements IGeniusCat {
    private final CatEntity cat;

    public GeniusCatFabric(CatEntity cat) {
        this.cat = cat;
    }

    @Override
    public boolean hasTotem() {
        return ((ICatEntityMixin) cat).catsplus$hasTotem();
    }

    @Override
    public void setTotem(boolean totem) {
        ((ICatEntityMixin) cat).catsplus$setTotem(totem);
    }

    @Override
    public int getFavorability(PlayerEntity player) {
        return ((ICatEntityMixin) cat).catsplus$getFavorability(player);
    }

    @Override
    public void setFavorability(int favorability, PlayerEntity player) {
        ((ICatEntityMixin) cat).catsplus$setFavorability(favorability, player);

        if (getFavorability(player) <= 0) {
            cat.tryAttack(player);
            cat.getWorld().sendEntityStatus(cat, EntityStatuses.ADD_VILLAGER_ANGRY_PARTICLES);

            if (cat.isOwner(player)) {
                cat.setOwnerUuid(null);
                cat.setTamed(false);
                cat.setSitting(false);
                setLives(0);
                cat.onTamedChanged();
            }
        }
    }

    @Override
    public int getLives() {
        return ((ICatEntityMixin) cat).catsplus$getLives();
    }

    @Override
    public void setLives(int lives) {
        ((ICatEntityMixin) cat).catsplus$setLives(lives);
    }
}
