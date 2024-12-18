package cuteneko.catsplus.fabric.mixins.mixin;

import cuteneko.catsplus.fabric.mixins.impl.IPlayerEntityMixin;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements IPlayerEntityMixin {
    @Unique
    private CatEntity catsplus$catEntity = null;

    @Unique
    private boolean catsplus$isCat = false;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public CatEntity catsplus$getCatEntity() {
        if (catsplus$catEntity == null) {
            int variant = (int) (uuid.getLeastSignificantBits() % 11);

            if (variant < 0) {
                variant += 11;
            }

            var key = switch (variant) {
                case 0 -> CatVariant.TABBY;
                case 1 -> CatVariant.BLACK;
                case 2 -> CatVariant.RED;
                case 3 -> CatVariant.SIAMESE;
                case 4 -> CatVariant.BRITISH_SHORTHAIR;
                case 5 -> CatVariant.CALICO;
                case 6 -> CatVariant.PERSIAN;
                case 7 -> CatVariant.RAGDOLL;
                case 8 -> CatVariant.WHITE;
                case 9 -> CatVariant.JELLIE;
                case 10 -> CatVariant.ALL_BLACK;
                default -> throw new IllegalArgumentException("Invalid variant: " + variant);
            };

            catsplus$catEntity = EntityType.CAT.create(getWorld());

            if (catsplus$catEntity != null) {
                catsplus$catEntity.setVariant(Registries.CAT_VARIANT.get(key));
            }

        }

        return this.catsplus$catEntity;
    }

    @Override
    public void catsplus$setCatEntity(CatEntity cat) {
        catsplus$catEntity = cat;
    }

    @Override
    public boolean catsplus$isCat() {
        return catsplus$isCat;
    }

    @Override
    public void catsplus$setCat(boolean cat) {
        catsplus$isCat = cat;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void write(NbtCompound nbt, CallbackInfo ci) {
        var tag = new NbtCompound();

        var catTag = new NbtCompound();
        catsplus$getCatEntity().saveNbt(catTag);
        tag.put(Constants.TAG_CAT_PLAYER_INNER_CAT, catTag);

        tag.putBoolean(Constants.TAG_CAT_PLAYER_IS_CAT, catsplus$isCat);

        nbt.put(Constants.CAP_CAT_PLAYER.toString(), tag);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void read(NbtCompound nbt, CallbackInfo ci) {
        var tag = nbt.getCompound(Constants.CAP_CAT_PLAYER.toString());

        var catTag = tag.getCompound(Constants.TAG_CAT_PLAYER_INNER_CAT);
        catsplus$getCatEntity().readNbt(catTag);
        catsplus$isCat = tag.getBoolean(Constants.TAG_CAT_PLAYER_IS_CAT);
    }
}
