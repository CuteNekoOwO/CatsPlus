package cuteneko.catsplus.item;

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
        if (!stack.hasNbt() || !Objects.requireNonNull(stack.getNbt()).contains(Constants.TAG_CAT_BAG_CAT)) {
            tooltip.add(Text.translatable(Constants.MESSAGE_CAT_BAG_NO_CAT).formatted(Formatting.DARK_GRAY));
            return;
        }

        var cat = stack.getNbt().getCompound(Constants.TAG_CAT_BAG_CAT);
        if (cat.contains(Constants.TAG_CUSTOM_NAME)) {
            var name = cat.getString(Constants.TAG_CUSTOM_NAME);
            var component = Text.Serialization.fromJson(name);

            if (component != null) {
                tooltip.add(Text.translatable(Constants.MESSAGE_CAT_BAG_HAS_CAT_NAMED, component.getString()).formatted(Formatting.BLUE));
                return;
            }
        }

        tooltip.add(Text.translatable(Constants.MESSAGE_CAT_BAG_HAS_CAT).formatted(Formatting.BLUE));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var stack = context.getStack();
        if (!stack.hasNbt()) {
            return ActionResult.PASS;
        }

        if (stack.hasCustomName()) {
            var name = stack.getName().getString();
            if (name.equals("MeowBot233") || name.equals("Fang_Luo")) {
                stack.removeCustomName();
                Objects.requireNonNull(context.getPlayer()).giveItemStack(new ItemStack(ModItems.FANG_LUO));
                return ActionResult.SUCCESS;
            }
        }

        var nbt = stack.getNbt();
        if (!(nbt != null && nbt.contains(Constants.TAG_CAT_BAG_CAT))) {
            return ActionResult.PASS;
        }

        var catNbt = nbt.getCompound(Constants.TAG_CAT_BAG_CAT);
        Direction direction = context.getSide();
        var world = context.getWorld();
        var player = context.getPlayer();
        var pos = context.getBlockPos().offset(direction);

        if (world instanceof ServerWorld) {
            this.spawnEntity((ServerWorld) world, catNbt, pos);
            world.emitGameEvent(player, GameEvent.ENTITY_PLACE, pos);
        }

        nbt.remove(Constants.TAG_CAT_BAG_CAT);
        stack.setNbt(nbt);
        Objects.requireNonNull(context.getPlayer()).setStackInHand(context.getHand(), stack);
        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!(entity instanceof CatEntity cat)) {
            return ActionResult.PASS;
        }

        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            if (stack.getNbt().contains(Constants.TAG_CAT_BAG_CAT)) {
                return ActionResult.PASS;
            }
        }

        if (!cat.isOwner(user)) {
            return ActionResult.FAIL;
        }

        var nbt = stack.getOrCreateNbt();
        var catNbt = new NbtCompound();
        cat.setSitting(!cat.isSitting());
        cat.saveNbt(catNbt);
        nbt.put(Constants.TAG_CAT_BAG_CAT, catNbt);
        stack.setNbt(nbt);
        user.setStackInHand(hand, stack);
        cat.discard();
        return ActionResult.SUCCESS;
    }

    private void spawnEntity(ServerWorld world, NbtCompound nbt, BlockPos pos) {
        CatEntity cat = EntityType.CAT.spawn(world, nbt, null, pos, SpawnReason.EVENT, true, false);
        assert cat != null;
        assert nbt != null;
        var x = NbtDouble.of(cat.getX());
        var y = NbtDouble.of(cat.getY());
        var z = NbtDouble.of(cat.getZ());
        var list = new NbtList();
        list.add(x);
        list.add(y);
        list.add(z);
        nbt.put(Constants.TAG_ENTITY_POS, list);
        cat.readNbt(nbt);
    }
}
