package cuteneko.catsplus.item;

import cuteneko.catsplus.data.component.CatContainer;
import cuteneko.catsplus.data.component.ModComponents;
import cuteneko.catsplus.item.group.ModItemGroups;
import cuteneko.catsplus.utility.ComponentHelper;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CatBagItem extends Item {

    public CatBagItem() {
        super(new Item.Properties()
                .fireResistant()
                .stacksTo(1)
                .component(DataComponents.DYED_COLOR, new DyedItemColor(DyedItemColor.LEATHER_COLOR, false))
                .component(ModComponents.CAT_CONTAINER.get(), null)
                .arch$tab(ModItemGroups.CATS_PLUS));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        var catContainer = ComponentHelper.getCatContainer(stack);
        if (catContainer == null) {
            tooltip.add(Component.translatable(Constants.MESSAGE_CAT_BAG_DESCRIPTION_NO_CAT).withStyle(ChatFormatting.DARK_GRAY));
            return;
        }

        if (catContainer.hasCustomName()) {
            tooltip.add(Component.translatable(Constants.MESSAGE_CAT_BAG_DESCRIPTION_HAS_NAMED_CAT, catContainer.customName().getString()).withStyle(ChatFormatting.BLUE));
        } else {
            tooltip.add(Component.translatable(Constants.MESSAGE_CAT_BAG_DESCRIPTION_HAS_CAT).withStyle(ChatFormatting.BLUE));
        }
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        var stack = context.getItemInHand();
        var name = ComponentHelper.getCustomName(stack);
        var player = context.getPlayer();
        if (player == null) {
            return InteractionResult.PASS;
        }

        if (name != null) {
            var str = name.getString();
            if ("MeowBot233".equalsIgnoreCase(str)
                    || "Fang_Luo".equalsIgnoreCase(str)
                    || "坊洛".equalsIgnoreCase(str)) {
                ComponentHelper.removeCustomName(stack);
                player.addItem(new ItemStack(ModItems.FANG_LUO));
                return InteractionResult.SUCCESS;
            }
        }

        var catContainer = ComponentHelper.getCatContainer(stack);
        if (catContainer == null) {
            return InteractionResult.PASS;
        }

        var direction = context.getClickedFace();
        var level = context.getLevel();
        var spawnPos = context.getClickedPos().relative(direction);
        if (!level.isLoaded(spawnPos) || !level.getBlockState(spawnPos).isAir()) {
            // Todo: Fail particle.
            return InteractionResult.FAIL;
        }

        if (level instanceof ServerLevel) {
            var cat = catContainer.createCat(level);
            cat.setPos(spawnPos.getBottomCenter());
            cat.setOrderedToSit(true);
            level.addFreshEntity(cat);
            level.gameEvent(player, GameEvent.ENTITY_PLACE, spawnPos);
        }

        ComponentHelper.removeCatContainer(stack);
        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity,
                                                           InteractionHand usedHand) {
        if (ComponentHelper.getCatContainer(stack) != null) {
            return InteractionResult.PASS;
        }

        if (entity instanceof Cat cat) {
            if (!cat.isOwnedBy(player)) {
                // Todo: Fail particle.
                return InteractionResult.FAIL;
            }

            cat.setOrderedToSit(true);
            var catContainer = new CatContainer(cat);
            ComponentHelper.setCatContainer(stack, catContainer);
            cat.discard();
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
