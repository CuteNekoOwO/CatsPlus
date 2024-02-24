package cuteneko.catsplus.mixins.mixin.totemeow;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.utility.Constants;
import cuteneko.catsplus.utility.GeniusCatHelper;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.OffsetDateTime;

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

    @Inject(method = "onDeath", at = @At("RETURN"))
    private void afterDeath(DamageSource damageSource, CallbackInfo ci) {
        if ((Object) this instanceof CatEntity cat) {
            if (cat.getOwnerUuid() == null) {
                return;
            }

            var stack = new ItemStack(ModItems.CAT_SPIRIT);
            var spirit = CatsPlusData.getCatSpirit(stack);

            spirit.setCat(cat);
            spirit.setDeathTime(OffsetDateTime.now());
            spirit.setDeathMessage(getDamageTracker().getDeathMessage());

            var owner = cat.getOwner();
            if (owner == null) {
                CatsPlusData.getCatServer(cat.getServer()).addCatSpirit(cat.getOwnerUuid(), stack);
                return;
            }

            if (owner instanceof ServerPlayerEntity player) {
                player.giveItemStack(stack);
                player.sendMessage(Text.translatable(Constants.MESSAGE_CAT_DIED));
            }
        }
    }
}
