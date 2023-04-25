package cuteneko.catsplus.mixin;

import cuteneko.catsplus.impl.CatEntityMixinImpl;
import cuteneko.catsplus.impl.PlayerEntityMixinImpl;
import cuteneko.catsplus.item.MyItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.packet.s2c.play.EntityPassengersSetS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;


@Mixin(CatEntity.class)
public abstract class CatEntityMixin extends TameableEntity implements CatEntityMixinImpl {

    @Shadow public abstract boolean tryAttack(Entity target);

    @Shadow protected abstract void onTamedChanged();

    private int favorability = 0;

    private int lives = 9;


    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void setLives(int lives) {
        this.lives = lives;
    }

    private boolean respawnable = false;

    @Override
    public boolean getRespawnable() {
        return this.respawnable;
    }

    @Override
    public void setRespawnable(boolean respawnable) {
        this.respawnable = respawnable;
    }


    private boolean totem = false;

    public boolean hasTotem() {
        return totem;
    }
    @Override
    protected boolean tryUseTotem(DamageSource source) {
        if(this.hasTotem()) {
            this.setHealth(1.0f);
            this.clearStatusEffects();
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
            this.world.sendEntityStatus(this, EntityStatuses.USE_TOTEM_OF_UNDYING);
            this.totem = false;
            return true;
        }
        return super.tryUseTotem(source);
    }

    @Override
    public void setFavorability(int favorability, PlayerEntity player) {
        this.favorability = Math.min(favorability, 100);
        if(this.favorability <= 0) {
            this.tryAttack(player);
            this.setOwnerUuid(null);
            this.setTamed(false);
            this.setSitting(false);
            this.setRespawnable(false);
            this.onTamedChanged();
            this.world.sendEntityStatus(this, EntityStatuses.ADD_VILLAGER_ANGRY_PARTICLES);
            if (player instanceof ServerPlayerEntity) {
                Criteria.TAME_ANIMAL.trigger((ServerPlayerEntity)player, this);
            }
        }
    }

    protected void produceParticles(ParticleEffect parameters) {
        for (int i = 0; i < 5; ++i) {
            double d = this.random.nextGaussian() * 0.02;
            double e = this.random.nextGaussian() * 0.02;
            double f = this.random.nextGaussian() * 0.02;
            this.world.addParticle(parameters, this.getParticleX(1.0), this.getRandomBodyY() + 1.0, this.getParticleZ(1.0), d, e, f);
        }
    }

    public void addFavorability(int favorability, PlayerEntity player) {
        setFavorability(this.favorability + favorability, player);
    }

    @Override
    public int getFavorability() { return this.favorability; }
    private boolean songPlaying;
    @Nullable
    private BlockPos songSource;

    private static final Ingredient TAMED_INGREDIENT = Ingredient.ofItems(
            Items.COD,
            Items.SALMON,
            Items.COOKED_COD,
            Items.COOKED_SALMON,
            Items.TROPICAL_FISH);

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("Favorability", favorability);
        nbt.putInt("Lives", lives);
        nbt.putBoolean("Respawnable", respawnable);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if(nbt.contains("Favorability", NbtElement.INT_TYPE)) {
            this.favorability = nbt.getInt("Favorability");
            this.lives = nbt.getInt("Lives");
            this.respawnable = nbt.getBoolean("Respawnable");
        }
    }

    @Inject(method = "eat", at = @At("TAIL"))
    protected void eat(PlayerEntity player, Hand hand, ItemStack stack, CallbackInfo ci) {
        if(this.isOwner(player)) {
            addFavorability(Objects.requireNonNull(stack.getItem().getFoodComponent()).getHunger(), player);
        }
    }

    protected CatEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "isBreedingItem", at = @At("RETURN"), cancellable = true)
    public void isBreedingItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(this.isTamed()) cir.setReturnValue(TAMED_INGREDIENT.test(stack));
    }

    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/CatEntity;setSitting(Z)V"), cancellable = true)
    public void interactMobSetSitting(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if(player.isSneaking() && !player.hasPassengers() && !((PlayerEntityMixinImpl)player).isCat()) {
            ((ServerPlayerEntity)player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(this));
            this.setSitting(false);
            this.startRiding(player);
            ((ServerPlayerEntity)player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(player));
            cir.setReturnValue(ActionResult.SUCCESS);
            cir.cancel();
            return;
        }
        //Not working since you can never click the cat on your head!!
        else if(player.getFirstPassenger() == this) {
            ((ServerPlayerEntity)player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(this));
            this.stopRiding();
            ((ServerPlayerEntity)player).networkHandler.sendPacket(new EntityPassengersSetS2CPacket(player));
            cir.setReturnValue(ActionResult.SUCCESS);
            return;
        }
        ItemStack itemStack = player.getStackInHand(hand);
        if(itemStack.isOf(MyItems.TOTEMEOW) && !this.hasTotem()) {
            itemStack.decrement(1);
            this.totem = true;
            player.setStackInHand(hand, itemStack);
            cir.setReturnValue(ActionResult.SUCCESS);
            cir.cancel();
        }
    }


        @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/CatEntity;setOwner(Lnet/minecraft/entity/player/PlayerEntity;)V"))
    public void interactMobSetOwner(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        this.favorability = 50;
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.ADD_VILLAGER_ANGRY_PARTICLES) {
            this.produceParticles(ParticleTypes.ANGRY_VILLAGER);
        } else {
            super.handleStatus(status);
        }
    }

    @Override
    public void tickMovement() {
        if (this.songSource == null || !this.songSource.isWithinDistance(this.getPos(), 3.46) || !this.world.getBlockState(this.songSource).isOf(Blocks.JUKEBOX)) {
            this.songPlaying = false;
            this.songSource = null;
        }
        super.tickMovement();
    }



    @Override
    public void setNearbySongPlaying(BlockPos songPosition, boolean playing) {
        this.songSource = songPosition;
        this.songPlaying = playing;

    }

    @Override
    public boolean isSongPlaying() {
        return this.songPlaying;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        var result =  super.damage(source, amount);
        if(!result) return false;
        if(source.getSource() instanceof PlayerEntity player) {
            if(this.isOwner(player)) {
                addFavorability(-(int) (amount * 5), player);
            }
        }
        return true;
    }


    @Mixin(targets = "net.minecraft.entity.passive.CatEntity$SleepWithOwnerGoal")
    abstract static class SleepWithOwnerGoal extends Goal {
        @Shadow @Final private CatEntity cat;

        @Inject(method = "dropMorningGifts", at = @At("TAIL"))
        private void dropMorningGifts(CallbackInfo ci) {
            ((CatEntityMixinImpl)cat).setRespawnable(true);
        }
    }

}
