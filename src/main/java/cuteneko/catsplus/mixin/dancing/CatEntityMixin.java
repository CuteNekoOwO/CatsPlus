package cuteneko.catsplus.mixin.dancing;

import cuteneko.catsplus.bridge.IMusicianCat;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Cat.class)
public abstract class CatEntityMixin extends TamableAnimal implements IMusicianCat {
    @Unique
    private static final EntityDataAccessor<Boolean> CATSPLUS$SOUND_PLAYING = SynchedEntityData.defineId(Cat.class, EntityDataSerializers.BOOLEAN);
    @Unique
    private static final EntityDataAccessor<BlockPos> CATSPLUS$SOUND_SOURCE = SynchedEntityData.defineId(Cat.class, EntityDataSerializers.BLOCK_POS);

    protected CatEntityMixin(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void catsplus$defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(CATSPLUS$SOUND_PLAYING, false);
        builder.define(CATSPLUS$SOUND_SOURCE, BlockPos.ZERO);
    }
}
