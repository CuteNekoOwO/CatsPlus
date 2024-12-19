package cuteneko.catsplus.item;

import cuteneko.catsplus.data.component.ModComponents;
import cuteneko.catsplus.item.group.ModItemGroups;
import cuteneko.catsplus.utility.ComponentHelper;
import cuteneko.catsplus.utility.ModConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CatSpiritItem extends Item {
    public CatSpiritItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .fireResistant()
                .rarity(Rarity.EPIC)
                .component(ModComponents.CAT_CONTAINER.get(), null)
                .component(ModComponents.CAT_SPIRIT.get(), null)
                .arch$tab(ModItemGroups.CATS_PLUS));
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        var catContainer = ComponentHelper.getCatContainer(stack);
        if (catContainer != null) {
            if (catContainer.hasCustomName()) {
                return Component.translatable(ModConstants.MESSAGE_CAT_SPIRIT_NAME, catContainer.customName().getString());
            }
        }
        return super.getName(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {

        var catSpirit = ComponentHelper.getCatSpirit(stack);
        if (catSpirit != null) {
            tooltip.add(catSpirit.reason());
            var time = catSpirit.time();
            tooltip.add(Component.translatable(ModConstants.MESSAGE_CAT_SPIRIT_DESCRIPTION_TIME_LABEL)
                    .append(Component.translatable(ModConstants.MESSAGE_CAT_SPIRIT_DESCRIPTION_TIME_PATTERN, time.getYear(), time.getMonth().getValue(), time.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond())));
        }


        super.appendHoverText(stack, context, tooltip, tooltipFlag);
    }
}
