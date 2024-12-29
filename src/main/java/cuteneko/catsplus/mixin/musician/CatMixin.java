package cuteneko.catsplus.mixin.musician;

import cuteneko.catsplus.bridge.ICatBridge;
import cuteneko.catsplus.utility.ModConstants;
import cuteneko.catsplus.utility.ParticleHelper;
import cuteneko.catsplus.utility.TagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Cat.class)
public abstract class CatMixin extends TamableAnimal implements ICatBridge {
    @Unique
    private static final EntityDataAccessor<Boolean> CATSPLUS$SOUND_PLAYING = SynchedEntityData.defineId(Cat.class, EntityDataSerializers.BOOLEAN);

    @Unique
    private static final EntityDataAccessor<BlockPos> CATSPLUS$SOUND_SOURCE = SynchedEntityData.defineId(Cat.class, EntityDataSerializers.BLOCK_POS);

    protected CatMixin(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void catsplus$defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(CATSPLUS$SOUND_PLAYING, false);
        builder.define(CATSPLUS$SOUND_SOURCE, BlockPos.ZERO);
    }

    @Override
    public boolean catsplus$isSoundPlaying() {
        return entityData.get(CATSPLUS$SOUND_PLAYING);
    }

    @Override
    public @NotNull BlockPos catsplus$getSoundSource() {
        return entityData.get(CATSPLUS$SOUND_SOURCE);
    }

    @Override
    public void catsplus$startSound(@NotNull BlockPos pos) {
        entityData.set(CATSPLUS$SOUND_PLAYING, true);
        entityData.set(CATSPLUS$SOUND_SOURCE, pos);
    }

    @Override
    public void catsplus$stopSound() {
        entityData.set(CATSPLUS$SOUND_PLAYING, false);
        entityData.set(CATSPLUS$SOUND_SOURCE, BlockPos.ZERO);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void catsplus$readAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        compound.putBoolean(ModConstants.TAG_CAT_SOUND_PLAYING, catsplus$isSoundPlaying());
        compound.put(ModConstants.TAG_CAT_SOUND_SOURCE, TagHelper.saveBlockPos(catsplus$getSoundSource()));
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void catsplus$addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        var playing = compound.getBoolean(ModConstants.TAG_CAT_SOUND_PLAYING);
        var pos = TagHelper.loadBlockPos(compound.getCompound(ModConstants.TAG_CAT_SOUND_SOURCE));

        if (playing && pos != null) {
            catsplus$startSound(pos);
        } else {
            catsplus$stopSound();
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void catsplus$tick(CallbackInfo ci) {
        if (catsplus$isSoundPlaying() && this.tickCount % 25 == 0) {
            ParticleHelper.catMusic((Cat) (Object) this);
        }
    }
}
