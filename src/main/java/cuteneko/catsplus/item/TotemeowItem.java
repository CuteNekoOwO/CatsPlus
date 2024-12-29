package cuteneko.catsplus.item;

import cuteneko.catsplus.CatsPlusData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TotemeowItem extends Item {
    public TotemeowItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        if (entity instanceof Cat cat) {
            var geniusCat = CatsPlusData.getGeniusCat(cat);
            if (geniusCat.hasTotem()) {
                return InteractionResult.FAIL;
            }

            geniusCat.setTotem(true);
            stack.shrink(1);
            return InteractionResult.SUCCESS;
        }

        return super.interactLivingEntity(stack, player, entity, hand);
    }
}
