package cuteneko.catsplus.forge.capability;

import cuteneko.catsplus.data.ICatPlayer;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraftforge.common.util.INBTSerializable;

public class CatPlayerCapability implements ICatPlayer, INBTSerializable<NbtCompound> {
    private CatEntity catEntity = null;
    private boolean isCat = false;

    public CatPlayerCapability(PlayerEntity player) {
        init(player);
    }

    private void init(PlayerEntity player) {
        int variant = (int) (player.getUuid().getLeastSignificantBits() % 11);

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

        catEntity = EntityType.CAT.create(player.getWorld());

        if (catEntity != null) {
            catEntity.setVariant(Registries.CAT_VARIANT.get(key));
        }
    }

    @Override
    public CatEntity getCatEntity() {
        return catEntity;
    }

    @Override
    public void setCatEntity(CatEntity cat) {
        this.catEntity = cat;
    }

    @Override
    public boolean isCat() {
        return isCat;
    }

    @Override
    public void setCat(boolean cat) {
        isCat = cat;
    }

    @Override
    public NbtCompound serializeNBT() {
        var tag = new NbtCompound();

        var catTag = new NbtCompound();
        getCatEntity().saveNbt(catTag);
        tag.put(Constants.TAG_CAT_PLAYER_INNER_CAT, catTag);

        tag.putBoolean(Constants.TAG_CAT_PLAYER_IS_CAT, isCat);
        return tag;
    }

    @Override
    public void deserializeNBT(NbtCompound tag) {
        var catTag = tag.getCompound(Constants.TAG_CAT_PLAYER_INNER_CAT);
        getCatEntity().readNbt(catTag);
        isCat = tag.getBoolean(Constants.TAG_CAT_PLAYER_IS_CAT);
    }
}
