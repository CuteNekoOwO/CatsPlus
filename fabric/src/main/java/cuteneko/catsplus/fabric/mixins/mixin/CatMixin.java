package cuteneko.catsplus.fabric.mixins.mixin;

import cuteneko.catsplus.data.entity.GeniusCat;
import cuteneko.catsplus.fabric.mixins.impl.ICatEntityMixin;
import cuteneko.catsplus.utility.ModConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Cat.class)
public abstract class CatMixin extends TamableAnimal implements ICatEntityMixin {

    @Unique
    private GeniusCat catsplus$geniusCat;

    protected CatMixin(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void catsplus$init(EntityType<?> entityType, Level level, CallbackInfo ci) {
        catsplus$geniusCat = new GeniusCat((Cat) (Object) this);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    private void catsplus$addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        compound.put(ModConstants.TAG_GENIUS_CAT, catsplus$geniusCat.serializeTag(level().registryAccess()));
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    private void read(CompoundTag compound, CallbackInfo ci) {
        var tag = compound.getCompound(ModConstants.TAG_GENIUS_CAT);
        catsplus$geniusCat.deserializeTag(tag, level().registryAccess());
    }

    @Unique
    public GeniusCat getCatsplus$geniusCat() {
        return catsplus$geniusCat;
    }
}
