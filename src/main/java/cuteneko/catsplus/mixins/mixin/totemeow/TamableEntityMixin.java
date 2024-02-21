package cuteneko.catsplus.mixins.mixin.totemeow;

import cuteneko.catsplus.CatsPlusPlatform;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.utility.GeniusCatHelper;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TameableEntity.class)
public abstract class TamableEntityMixin extends AnimalEntity {
    protected TamableEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "handleStatus", at = @At("HEAD"), cancellable = true)
    private void beforeHandleStatus(byte status, CallbackInfo ci) {
        if ((Object) this instanceof CatEntity) {
            if (status == EntityStatuses.ADD_VILLAGER_ANGRY_PARTICLES) {
                GeniusCatHelper.produceParticles(this, ParticleTypes.ANGRY_VILLAGER);
                ci.cancel();
            }
        }
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void beforeDeath(DamageSource damageSource, CallbackInfo ci) {
        // Todo: qyl27: More plan here.
        if ((Object) this instanceof CatEntity cat) {
            var geniusCat = CatsPlusPlatform.getGeniusCat(cat);

            geniusCat.setLives(geniusCat.getLives() - 1);

            if(geniusCat.canRespawn() && geniusCat.getLives() > 0) {
                var stack = new ItemStack(ModItems.CAT_SPIRIT);
                stack.setCount(1);
                var nbt = stack.getOrCreateNbt();
                var catNbt = new NbtCompound();
                this.saveNbt(catNbt);
                nbt.put("Cat", catNbt);
                stack.setNbt(nbt);
                this.dropStack(stack);
            }
        }
    }
}
