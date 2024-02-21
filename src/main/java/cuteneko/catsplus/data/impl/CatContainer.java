package cuteneko.catsplus.data.impl;

import cuteneko.catsplus.data.ICatContainerItem;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public abstract class CatContainer implements ICatContainerItem {
    protected ItemStack stack;

    public CatContainer(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public boolean hasCat() {
        var tag = stack.getNbt();
        if (tag != null) {
            var compoundCat = tag.get(Constants.TAG_CAT_CONTAINER);
            return compoundCat != null;
        }
        return false;
    }

    @Override
    public CatEntity getCat(World world) {
        var tag = stack.getNbt();
        if (tag != null) {
            var compoundCat = tag.getCompound(Constants.TAG_CAT_CONTAINER);
            var optionalEntity = EntityType.getEntityFromNbt(compoundCat, world);
            if (optionalEntity.isPresent() && optionalEntity.get() instanceof CatEntity cat) {
                cat.readNbt(compoundCat);
                return cat;
            }
        }

        return null;
    }

    @Override
    public void setCat(CatEntity cat) {
        var compoundCat = new NbtCompound();
        cat.saveNbt(compoundCat);
        var tag = stack.getOrCreateNbt();
        tag.put(Constants.TAG_CAT_CONTAINER, compoundCat);
        stack.setNbt(tag);
    }

    @Override
    public void clearCat() {
        var tag = stack.getOrCreateNbt();
        tag.remove(Constants.TAG_CAT_CONTAINER);
        stack.setNbt(tag);
    }

    @Override
    public boolean hasCustomCatName() {
        var tag = stack.getNbt();
        if (tag != null) {
            var compoundCat = tag.getCompound(Constants.TAG_CAT_CONTAINER);
            return compoundCat != null && compoundCat.contains(Constants.TAG_CUSTOM_NAME);
        }
        return false;
    }

    @Override
    public Text getCustomCatName() {
        var tag = stack.getNbt();
        if (tag != null) {
            var compoundCat = tag.getCompound(Constants.TAG_CAT_CONTAINER);
            if (compoundCat != null) {
                var name = compoundCat.getString(Constants.TAG_CUSTOM_NAME);
                return Text.Serialization.fromJson(name);
            }
        }
        return null;
    }
}
