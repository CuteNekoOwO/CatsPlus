package cuteneko.catsplus.data;

import cuteneko.catsplus.data.abstracted.ICatContainerItem;
import net.minecraft.text.Text;

import java.time.OffsetDateTime;

public interface ICatSpirit extends ICatContainerItem {
    OffsetDateTime getDeathTime();
    void setDeathTime(OffsetDateTime time);

    Text getDeathMessage();
    void setDeathMessage(Text message);
}
