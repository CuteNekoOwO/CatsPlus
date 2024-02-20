package cuteneko.catsplus.item;

import cuteneko.catsplus.utility.Constants;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FangLuoItem extends Item {
    public FangLuoItem() {
        super(new Item.Settings()
                .maxCount(1)
                .fireproof()
                .rarity(Rarity.EPIC));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.translatable(Constants.MESSAGE_FANG_LUO_DESCRIPTION).formatted(Formatting.GRAY));
    }
}
