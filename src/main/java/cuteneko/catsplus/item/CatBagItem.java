package cuteneko.catsplus.item;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.item.group.ModItemGroups;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class CatBagItem extends Item implements DyeableItem {

    public CatBagItem() {
        super(new Item.Settings()
                .fireproof()
                .maxCount(1)
                .arch$tab(ModItemGroups.CATS_PLUS));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (!stack.hasNbt() || !Objects.requireNonNull(stack.getNbt()).contains(Constants.TAG_CAT_CONTAINER)) {
            tooltip.add(Text.translatable(Constants.MESSAGE_CAT_BAG_DESCRIPTION_NO_CAT).formatted(Formatting.DARK_GRAY));
            return;
        }

        var bag = CatsPlusData.getCatBag(stack);
        if (bag.hasCustomCatName()) {
            tooltip.add(Text.translatable(Constants.MESSAGE_CAT_BAG_DESCRIPTION_HAS_NAMED_CAT, bag.getCustomCatName().getString()).formatted(Formatting.BLUE));
        } else {
            tooltip.add(Text.translatable(Constants.MESSAGE_CAT_BAG_DESCRIPTION_HAS_CAT).formatted(Formatting.BLUE));
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var stack = context.getStack();

        if (stack.hasCustomName()) {
            var name = stack.getName().getString();
            if ("MeowBot233".equalsIgnoreCase(name)
                    || "Fang_Luo".equalsIgnoreCase(name)
                    || "坊洛".equalsIgnoreCase(name)) {
                stack.removeCustomName();
                Objects.requireNonNull(context.getPlayer()).giveItemStack(new ItemStack(ModItems.FANG_LUO));
                return ActionResult.SUCCESS;
            }
        }

        var bag = CatsPlusData.getCatBag(stack);
        if (!bag.hasCat()) {
            return ActionResult.PASS;
        }

        Direction direction = context.getSide();
        var world = context.getWorld();
        var player = context.getPlayer();
        var pos = context.getBlockPos().offset(direction).toCenterPos();

        if (world instanceof ServerWorld) {
            var cat = bag.getCat(world);
            cat.setPosition(pos);
            world.spawnEntity(cat);
            world.emitGameEvent(player, GameEvent.ENTITY_PLACE, pos);
        }

        bag.clearCat();
        Objects.requireNonNull(context.getPlayer()).setStackInHand(context.getHand(), stack);
        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof CatEntity cat) {
            var bag = CatsPlusData.getCatBag(stack);
            if (!bag.hasCat()) {
                if (cat.isOwner(user)) {
                    cat.setSitting(false);
                    bag.setCat(cat);
                    cat.discard();
                    return ActionResult.SUCCESS;
                } else {
                    return ActionResult.FAIL;
                }
            }
        }

        return ActionResult.PASS;
    }
}
