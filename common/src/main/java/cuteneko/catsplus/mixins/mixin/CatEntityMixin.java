package cuteneko.catsplus.mixins.mixin;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.utility.GeniusCatHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityPassengersSetS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(CatEntity.class)
public abstract class CatEntityMixin extends TameableEntity {
    protected CatEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "isBreedingItem", at = @At("RETURN"), cancellable = true)
    public void isBreedingItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (this.isTamed()) {
            cir.setReturnValue(GeniusCatHelper.TAMED_CAT_FOODS.test(stack));
        }
    }

    @Inject(method = "eat", at = @At("TAIL"))
    protected void eat(PlayerEntity player, Hand hand, ItemStack stack, CallbackInfo ci) {
        var geniusCat = CatsPlus.getInstance().getPlatform().getGeniusCat((CatEntity) (Object) this);

        if (this.isOwner(player)) {
            geniusCat.addFavorability(Objects.requireNonNull(stack.getItem().getFoodComponent()).getHunger(), player);
        }
    }

    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/CatEntity;setSitting(Z)V"), cancellable = true)
    public void invoke$InteractMobSetSitting(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        var catPlayer = CatsPlus.getInstance().getPlatform().getCatPlayer(player);
        var geniusCat = CatsPlus.getInstance().getPlatform().getGeniusCat((CatEntity) (Object) this);

        if (player.isSneaking() && !player.hasPassengers() && !catPlayer.isCat()) {
            ((ServerPlayerEntity) player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(this));
            this.setSitting(false);
            this.startRiding(player);
            ((ServerPlayerEntity) player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(player));
            cir.setReturnValue(ActionResult.SUCCESS);
            cir.cancel();
            return;
        } else if (player.getFirstPassenger() == this) {    // Not working since you can never click the cat on your head!!
            ((ServerPlayerEntity) player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(this));
            this.stopRiding();
            ((ServerPlayerEntity) player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(player));
            cir.setReturnValue(ActionResult.SUCCESS);
            return;
        }

        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(ModItems.TOTEMEOW) && !geniusCat.hasTotem()) {
            itemStack.decrement(1);
            geniusCat.setTotem(true);
            player.setStackInHand(hand, itemStack);
            cir.setReturnValue(ActionResult.SUCCESS);
            cir.cancel();
        }
    }

    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/CatEntity;setOwner(Lnet/minecraft/entity/player/PlayerEntity;)V"))
    public void invokeInteractMobSetOwner(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        var geniusCat = CatsPlus.getInstance().getPlatform().getGeniusCat((CatEntity) (Object) this);
        geniusCat.setFavorability(50, player);
    }
}
