package cuteneko.catsplus.listener;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.utility.Constants;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class CatSpiritListener {
    public CatSpiritListener() {
//        TickEvent.PLAYER_POST.register(this::onPlayerTick);
        PlayerEvent.PLAYER_JOIN.register(this::onPlayerJoin);
    }

    private void onPlayerJoin(ServerPlayerEntity player) {
        var catServer = CatsPlusData.getCatServer(player.server);
        var spirits = catServer.getCatSpiritsByOwner(player);
        for (var spirit : spirits) {
            player.giveItemStack(spirit);
            player.sendMessage(Text.translatable(Constants.MESSAGE_CAT_DIED));
        }
        catServer.clearCatSpiritsByOwner(player);
    }
}
