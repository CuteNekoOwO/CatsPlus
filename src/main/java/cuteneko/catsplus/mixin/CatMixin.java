package cuteneko.catsplus.mixin;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.tag.ModItemTags;
import cuteneko.catsplus.utility.CattifyHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Cat.class)
public abstract class CatMixin extends TamableAnimal {

    protected CatMixin(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "isFood", at = @At("RETURN"), cancellable = true)
    public void catsplus$isFood(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (isTame()) {
            cir.setReturnValue(stack.is(ModItemTags.COOKED_FISHES));
        }
    }

    @Inject(method = "usePlayerItem", at = @At("TAIL"))
    protected void catsplus$eat(Player player, InteractionHand hand, ItemStack stack, CallbackInfo ci) {
        if (!isTame()) {
            return;
        }

        var geniusCat = CatsPlusData.getGeniusCat((Cat) (Object) this);

        var intimacy = 1;

        var food = stack.get(DataComponents.FOOD);
        if (food != null) {
            intimacy *= food.nutrition();
        }

        if (this.isOwnedBy(player)) {
            intimacy *= 2;
        }

        geniusCat.addIntimacyWith(player, intimacy);
    }

    @Inject(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Cat;setOrderedToSit(Z)V"), cancellable = true)
    public void catsplus$mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (!isTame()) {
            return;
        }

        var geniusCat = CatsPlusData.getGeniusCat((Cat) (Object) this);

        if (player.isShiftKeyDown() && !player.isVehicle() && !CattifyHelper.cattified(player)) {
            // Todo
//            ((ServerPlayer) player).connection.send(new ClientboundSetPassengersPacket(this));
            this.setOrderedToSit(false);
            this.startRiding(player);
//            ((ServerPlayer) player).connection.send(new ClientboundSetPassengersPacket(player));
            cir.setReturnValue(InteractionResult.SUCCESS);
            cir.cancel();
            return;
        } else if (player.getFirstPassenger() == this) {    // Not working since you can never click the cat on your head!!
//            ((ServerPlayer) player).connection.send(new ClientboundSetPassengersPacket(this));
            this.stopRiding();
//            ((ServerPlayer) player).connection.send(new ClientboundSetPassengersPacket(player));
            cir.setReturnValue(InteractionResult.SUCCESS);
            return;
        }

        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(ModItems.TOTEMEOW.get()) && !geniusCat.hasTotem()) {
            itemStack.shrink(1);
            geniusCat.setTotem(true);
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
