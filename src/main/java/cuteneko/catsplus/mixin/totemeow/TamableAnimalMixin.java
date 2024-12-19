package cuteneko.catsplus.mixin.totemeow;

import cuteneko.catsplus.data.component.CatContainer;
import cuteneko.catsplus.data.component.CatSpirit;
import cuteneko.catsplus.data.level.LevelWithCats;
import cuteneko.catsplus.item.ModItems;
import cuteneko.catsplus.utility.ComponentHelper;
import cuteneko.catsplus.utility.ModConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.OffsetDateTime;

@Mixin(TamableAnimal.class)
public abstract class TamableAnimalMixin extends Animal {

    protected TamableAnimalMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "die", at = @At("RETURN"))
    private void catsplus$die(DamageSource damageSource, CallbackInfo ci) {
        if ((Object) this instanceof Cat cat) {
            if (cat.getOwnerUUID() == null) {
                return;
            }

            var level = cat.level();
            if (!(level instanceof ServerLevel serverLevel)) {
                return;
            }

            var stack = new ItemStack(ModItems.CAT_SPIRIT);
            ComponentHelper.setCatContainer(stack, new CatContainer(cat));
            ComponentHelper.setCatSpirit(stack, new CatSpirit(OffsetDateTime.now(), getCombatTracker().getDeathMessage()));

            var owner = cat.getOwner();
            if (owner == null) {
                var storage = LevelWithCats.getLevelWithCats(serverLevel);
                // Todo: add it after Cat Resurrection Station.
//                storage.addCatSpirit(cat.getOwnerUUID(), stack);
                return;
            }

            if (owner instanceof ServerPlayer player) {
                player.addItem(stack);
                player.sendSystemMessage(Component.translatable(ModConstants.MESSAGE_CAT_DIED));
            }
        }
    }
}
