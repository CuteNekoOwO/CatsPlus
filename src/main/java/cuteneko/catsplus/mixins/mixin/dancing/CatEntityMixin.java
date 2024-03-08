package cuteneko.catsplus.mixins.mixin.dancing;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.mixins.bridge.dancing.IMusicianCat;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CatEntity.class)
public abstract class CatEntityMixin extends TameableEntity implements IMusicianCat {
    @Unique
    private static final TrackedData<Boolean> CATSPLUS$SOUND_PLAYING = DataTracker.registerData(CatEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    @Unique
    private static final TrackedData<BlockPos> CATSPLUS$SOUND_SOURCE = DataTracker.registerData(CatEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);

    protected CatEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void afterInitDataTracker(CallbackInfo ci) {
        dataTracker.startTracking(CATSPLUS$SOUND_PLAYING, false);
        dataTracker.startTracking(CATSPLUS$SOUND_SOURCE, BlockPos.ORIGIN);
    }

    @Override
    public @Nullable BlockPos catsplus$getSoundSource() {
        if (dataTracker.get(CATSPLUS$SOUND_PLAYING)) {
            return dataTracker.get(CATSPLUS$SOUND_SOURCE);
        } else {
            return null;
        }
    }

    @Override
    public void catsplus$setSoundSource(@Nullable BlockPos pos) {
        dataTracker.set(CATSPLUS$SOUND_PLAYING, pos != null);

        if (pos != null) {
            dataTracker.set(CATSPLUS$SOUND_SOURCE, pos);
        }
    }
}
