package cuteneko.catsplus.item;

import cuteneko.catsplus.utility.Constants;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class FangLuoItem extends Item {
    public FangLuoItem() {
        super(new Item.Properties()
                .fireResistant()
                .rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltip, tooltipFlag);

        tooltip.add(Component.translatable(Constants.MESSAGE_FANG_LUO_DESCRIPTION_1).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable(Constants.MESSAGE_FANG_LUO_DESCRIPTION_2).withStyle(ChatFormatting.GRAY));
    }
}
