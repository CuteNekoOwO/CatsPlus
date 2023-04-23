package cuteneko.catsplus;

import cuteneko.catsplus.effect.MyEffects;
import cuteneko.catsplus.item.MyItemGroups;
import cuteneko.catsplus.item.MyItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemGroups;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CatsPlus implements ModInitializer {


    @Override
    public void onInitialize() {
        new MyItems();
        new MyItemGroups();
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.add(MyItems.CAT_BAG));
        new MyEffects();
        Registry.register(
                Registries.POTION,
                new Identifier("catsplus", "cattify"),
                new Potion(new StatusEffectInstance(MyEffects.CATTIFY, 1800))
        );
        Registry.register(
                Registries.POTION,
                new Identifier("catsplus", "long_cattify"),
                new Potion("cattify", new StatusEffectInstance(MyEffects.CATTIFY, 4800))
        );
    }
}
