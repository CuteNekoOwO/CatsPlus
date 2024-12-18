package cuteneko.catsplus.forge;

import cuteneko.catsplus.CatsPlus;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CatsPlus.MODID)
public class CatsPlusForge {
    private final CatsPlus mod;

    public CatsPlusForge() {
        mod = new CatsPlus();

        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::onSetup);
        bus.addListener(this::onClientSetup);

        EventBuses.registerModEventBus(CatsPlus.MODID, bus);
    }

    public void onSetup(FMLCommonSetupEvent event) {
        mod.init();
    }

    public void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(mod::initClient);
    }
}
