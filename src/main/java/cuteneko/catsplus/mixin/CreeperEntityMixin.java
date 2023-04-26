package cuteneko.catsplus.mixin;

import cuteneko.catsplus.impl.PlayerEntityMixinImpl;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity {

    private static final Predicate<LivingEntity> CATTIFY = entity -> (entity instanceof PlayerEntity) && ((PlayerEntityMixinImpl)entity).isCat();

    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    public void initGoals(CallbackInfo ci) {
        this.goalSelector.add(3, new FleeEntityGoal<>(this, PlayerEntity.class, 6.0f, 1.0, 1.2, CATTIFY));
    }

}
