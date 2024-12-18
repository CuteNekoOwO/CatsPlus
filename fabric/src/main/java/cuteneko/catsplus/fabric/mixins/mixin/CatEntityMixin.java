package cuteneko.catsplus.fabric.mixins.mixin;

import cuteneko.catsplus.fabric.mixins.impl.ICatEntityMixin;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mixin(CatEntity.class)
public abstract class CatEntityMixin implements ICatEntityMixin {
    @Unique
    private int catsplus$lives = 0;
    @Unique
    private boolean catsplus$hasTotem = false;

    @Unique
    private final Map<UUID, Integer> catsplus$favorability = new HashMap<>();

    @Override
    public boolean catsplus$hasTotem() {
        return catsplus$hasTotem;
    }

    @Override
    public void catsplus$setTotem(boolean totem) {
        catsplus$hasTotem = totem;
    }

    @Override
    public int catsplus$getFavorability(PlayerEntity player) {
        if (!catsplus$favorability.containsKey(player.getUuid())) {
            catsplus$favorability.put(player.getUuid(), 0);
        }

        return catsplus$favorability.get(player.getUuid());
    }

    @Override
    public void catsplus$setFavorability(int favorability, PlayerEntity player) {
        catsplus$favorability.put(player.getUuid(), favorability);
    }

    @Override
    public int catsplus$getLives() {
        return catsplus$lives;
    }

    @Override
    public void catsplus$setLives(int lives) {
        catsplus$lives = lives;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void write(NbtCompound nbt, CallbackInfo ci) {
        var tag = new NbtCompound();

        tag.putInt(Constants.TAG_GENIUS_CAT_LIVES, catsplus$lives);
        tag.putBoolean(Constants.TAG_GENIUS_CAT_TOTEM, catsplus$hasTotem);

        var favorability = new NbtList();
        for (var entry : catsplus$favorability.entrySet()) {
            var f = new NbtCompound();
            f.putUuid(Constants.TAG_UUID, entry.getKey());
            f.putInt(Constants.TAG_VALUE, entry.getValue());
            favorability.add(f);
        }
        tag.put(Constants.TAG_GENIUS_CAT_FAVORABILITY, favorability);

        nbt.put(Constants.CAP_GENIUS_CAT.toString(), tag);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void read(NbtCompound nbt, CallbackInfo ci) {
        var tag = nbt.getCompound(Constants.CAP_GENIUS_CAT.toString());

        catsplus$lives = tag.getInt(Constants.TAG_GENIUS_CAT_LIVES);
        catsplus$hasTotem = tag.getBoolean(Constants.TAG_GENIUS_CAT_TOTEM);

        var favorability = tag.getList(Constants.TAG_GENIUS_CAT_FAVORABILITY, NbtElement.LIST_TYPE);
        for (var entry : favorability) {
            if (entry instanceof NbtCompound e
                    && e.contains(Constants.TAG_UUID)
                    && e.contains(Constants.TAG_VALUE)) {
                var uuid = e.getUuid(Constants.TAG_UUID);
                var value = e.getInt(Constants.TAG_VALUE);

                catsplus$favorability.put(uuid, value);
            }
        }
    }
}
