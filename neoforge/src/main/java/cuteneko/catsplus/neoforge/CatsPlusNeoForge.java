package cuteneko.catsplus.neoforge;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.client.CatsPlusClient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(CatsPlus.MODID)
public class CatsPlusNeoForge {

    public CatsPlusNeoForge(IEventBus bus) {
        CatsPlus.init();

        bus.addListener(this::onClientSetup);
    }

    public void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(CatsPlusClient::initClient);
    }
}
