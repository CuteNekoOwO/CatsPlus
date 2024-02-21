package cuteneko.catsplus.data.impl;

import cuteneko.catsplus.data.ICatSpirit;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.time.OffsetDateTime;

public class CatSpirit extends CatContainer implements ICatSpirit {
    public CatSpirit(ItemStack stack) {
        super(stack);
    }

    @Override
    public OffsetDateTime getDeathTime() {
        var tag = stack.getNbt();
        if (tag != null) {
            var deathAt = tag.getString(Constants.TAG_DEATH_TIME);
            if (!deathAt.isBlank()) {
                return OffsetDateTime.parse(deathAt);
            }
        }
        return null;
    }

    @Override
    public void setDeathTime(OffsetDateTime time) {
        var tag = stack.getOrCreateNbt();
        tag.putString(Constants.TAG_DEATH_TIME, time.toString());
        stack.setNbt(tag);
    }

    @Override
    public Text getDeathMessage() {
        var tag = stack.getNbt();
        if (tag != null) {
            var deathMsg = tag.getString(Constants.TAG_DEATH_MESSAGE);
            if (!deathMsg.isBlank()) {
                return Text.Serialization.fromJson(deathMsg);
            }
        }
        return Text.empty();
    }

    @Override
    public void setDeathMessage(Text message) {
        var tag = stack.getOrCreateNbt();
        tag.putString(Constants.TAG_DEATH_MESSAGE, Text.Serialization.toJsonString(message));
        stack.setNbt(tag);
    }
}
