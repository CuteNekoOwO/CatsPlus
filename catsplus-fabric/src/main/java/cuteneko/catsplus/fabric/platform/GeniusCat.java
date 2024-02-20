package cuteneko.catsplus.fabric.platform;

import cuteneko.catsplus.fabric.mixins.impl.ICatEntityMixin;
import cuteneko.catsplus.platform.IGeniusCat;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class GeniusCat implements IGeniusCat {
    private final CatEntity cat;

    public GeniusCat(CatEntity cat) {
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
    public boolean canRespawn() {
        return ((ICatEntityMixin) cat).catsplus$canRespawn();
    }

    @Override
    public void setCanRespawn(boolean canRespawn) {
        ((ICatEntityMixin) cat).catsplus$setCanRespawn(canRespawn);
    }

    @Override
    public int getFavorability(PlayerEntity player) {
        return ((ICatEntityMixin) cat).catsplus$getFavorability(player);
    }

    @Override
    public void setFavorability(int favorability, PlayerEntity player) {
        ((ICatEntityMixin) cat).catsplus$setFavorability(favorability, player);

        if(getFavorability(player) <= 0) {
            cat.tryAttack(player);
            cat.setOwnerUuid(null);
            cat.setTamed(false);
            cat.setSitting(false);
            setCanRespawn(false);
            cat.onTamedChanged();
            cat.getWorld().sendEntityStatus(cat, EntityStatuses.ADD_VILLAGER_ANGRY_PARTICLES);
            if (player instanceof ServerPlayerEntity) {
                Criteria.TAME_ANIMAL.trigger((ServerPlayerEntity)player, cat);
            }
        }
    }

    @Override
    public void addFavorability(int value, PlayerEntity player) {
        setFavorability(getFavorability(player) + value, player);
    }

    @Override
    public void subFavorability(int value, PlayerEntity player) {
        setFavorability(getFavorability(player) - value, player);
    }

    @Override
    public int getLives() {
        return ((ICatEntityMixin) cat).catsplus$getLives();
    }

    @Override
    public void setLives(int lives) {
        ((ICatEntityMixin) cat).catsplus$setLives(lives);
    }

    @Override
    public boolean isSongPlaying() {
        return ((ICatEntityMixin) cat).catsplus$isSongPlaying();
    }

    @Override
    public BlockPos getSongSource() {
        return ((ICatEntityMixin) cat).catsplus$getSongSource();
    }

    @Override
    public void songStartPlay(BlockPos source) {
        ((ICatEntityMixin) cat).catsplus$setSongPlaying(true);
        ((ICatEntityMixin) cat).catsplus$setSongSource(source);
    }

    @Override
    public void songStopPlay() {
        ((ICatEntityMixin) cat).catsplus$setSongPlaying(false);
        ((ICatEntityMixin) cat).catsplus$setSongSource(null);
    }
}
