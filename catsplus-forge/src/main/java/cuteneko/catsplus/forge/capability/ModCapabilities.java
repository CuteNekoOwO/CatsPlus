package cuteneko.catsplus.forge.capability;

import cuteneko.catsplus.CatsPlus;
import cuteneko.catsplus.data.ICatPlayer;
import cuteneko.catsplus.data.IGeniusCat;
import cuteneko.catsplus.forge.capability.provider.CatPlayerProvider;
import cuteneko.catsplus.forge.capability.provider.GeniusCatProvider;
import cuteneko.catsplus.utility.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CatsPlus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModCapabilities {
    public static Capability<IGeniusCat> GENIUS_CAT = CapabilityManager.get(new CapabilityToken<>() {});
    public static Capability<ICatPlayer> CAT_PLAYER = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public void registerCaps(RegisterCapabilitiesEvent event) {
        event.register(IGeniusCat.class);
        event.register(ICatPlayer.class);
    }

    @SubscribeEvent
    public void onAttachEntityCaps(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof CatEntity) {
            var provider = new GeniusCatProvider();
            event.addCapability(Constants.CAP_GENIUS_CAT, provider);
            event.addListener(provider::invalidate);
        }

        if (event.getObject() instanceof PlayerEntity player) {
            var provider = new CatPlayerProvider(player);
            event.addCapability(Constants.CAP_CAT_PLAYER, provider);
            event.addListener(provider::invalidate);
        }
    }
}
