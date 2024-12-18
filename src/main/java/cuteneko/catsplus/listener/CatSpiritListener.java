package cuteneko.catsplus.listener;

import cuteneko.catsplus.CatsPlusData;
import cuteneko.catsplus.data.level.LevelWithCats;
import cuteneko.catsplus.utility.Constants;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class CatSpiritListener {
    public CatSpiritListener() {
        PlayerEvent.PLAYER_JOIN.register(this::onPlayerJoin);
    }

    private void onPlayerJoin(ServerPlayer player) {
        var level = player.serverLevel();
        var data = LevelWithCats.getLevelWithCats(level);
        var spirits = data.getCatSpiritsByOwner(player);
        for (var spirit : spirits) {
            player.addItem(spirit);
            player.sendSystemMessage(Component.translatable(Constants.MESSAGE_CAT_DIED));
        }
        catServer.clearCatSpiritsByOwner(player);
    }
}
