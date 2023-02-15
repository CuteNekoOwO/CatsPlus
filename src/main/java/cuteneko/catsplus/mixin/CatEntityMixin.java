package cuteneko.catsplus.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityPassengersSetS2CPacket;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(CatEntity.class)
public abstract class CatEntityMixin extends TameableEntity {

    private boolean songPlaying;
    @Nullable
    private BlockPos songSource;

//    @Final @Shadow @Mutable
//    private static Ingredient TAMING_INGREDIENT;

    private static final Ingredient TAMED_INGREDIENT = Ingredient.ofItems(
            Items.COD,
            Items.SALMON,
            Items.COOKED_COD,
            Items.COOKED_SALMON,
            Items.TROPICAL_FISH);

    protected CatEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

//    @Inject(method = "<clinit>", at = @At("TAIL"))
//    private static void clinit(CallbackInfo ci) {
//        TAMING_INGREDIENT = Ingredient.ofItems(MyItems.COD, MyItems.SALMON, MyItems.COOKED_COD, MyItems.COOKED_SALMON);
//
//    }

    @Inject(method = "isBreedingItem", at = @At("RETURN"), cancellable = true)
    public void isBreedingItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(this.isTamed()) cir.setReturnValue(TAMED_INGREDIENT.test(stack));
    }

    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/CatEntity;setSitting(Z)V"), cancellable = true)
    public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if(player.isSneaking() && !player.hasPassengers()) {
            ((ServerPlayerEntity)player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(this));
            this.setSitting(false);
            this.startRiding(player);
            ((ServerPlayerEntity)player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(player));
            cir.setReturnValue(ActionResult.SUCCESS);
        }
        else if(player.getFirstPassenger() == this) {
            ((ServerPlayerEntity)player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(this));
            this.stopRiding();
            ((ServerPlayerEntity)player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(player));
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }

    @Override
    public void setNearbySongPlaying(BlockPos songPosition, boolean playing) {
        this.songSource = songPosition;
        this.songPlaying = playing;
//        if(playing)
//            System.out.println(String.format("Song playing at %d %d %d", songPosition.getX(), songPosition.getY(), songPosition.getZ()));

    }

    @Override
    public void tickMovement() {
        if (this.songSource == null || !this.songSource.isWithinDistance(this.getPos(), 3.46) || !this.world.getBlockState(this.songSource).isOf(Blocks.JUKEBOX)) {
            this.songPlaying = false;
            this.songSource = null;
        }
        super.tickMovement();

        //this.setHeadDown(!this.isHeadDown());
    }



}
