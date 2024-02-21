package cuteneko.catsplus.fabric.platform;

import cuteneko.catsplus.effect.ModEffects;
import cuteneko.catsplus.fabric.mixins.impl.IPlayerEntityMixin;
import cuteneko.catsplus.platform.ICatPlayer;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CatPlayer implements ICatPlayer {
    private final PlayerEntity player;

    public CatPlayer(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public CatEntity getCatEntity() {
        return ((IPlayerEntityMixin) player).catsplus$getCatEntity();
    }

    @Override
    public void setCatEntity(CatEntity cat) {
        ((IPlayerEntityMixin) player).catsplus$setCatEntity(cat);
    }

    @Override
    public boolean isCat() {
        return ((IPlayerEntityMixin) player).catsplus$isCat() || player.hasStatusEffect(ModEffects.CATTIFY.get());
    }

    @Override
    public void setCat(boolean cat) {
        ((IPlayerEntityMixin) player).catsplus$setCat(cat);
    }
}
