package cuteneko.catsplus.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;

public record CatContainer(CompoundTag data, String variant, boolean hasCustomName, Component customName) {
    public static final Codec<CatContainer> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    CompoundTag.CODEC.fieldOf("cat").forGetter(CatContainer::data),
                    Codec.STRING.fieldOf("variant").forGetter(CatContainer::variant),
                    Codec.BOOL.fieldOf("hasCustomName").forGetter(CatContainer::hasCustomName),
                    ComponentSerialization.CODEC.fieldOf("customName").forGetter(CatContainer::customName)
            ).apply(instance, CatContainer::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CatContainer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.COMPOUND_TAG, CatContainer::data,
            ByteBufCodecs.STRING_UTF8, CatContainer::variant,
            ByteBufCodecs.BOOL, CatContainer::hasCustomName,
            ComponentSerialization.STREAM_CODEC, CatContainer::customName,
            CatContainer::new);

    public CatContainer(Cat cat) {
        this(cat.saveWithoutId(new CompoundTag()), cat.getVariant().getRegisteredName(), cat.hasCustomName(), cat.hasCustomName() ? cat.getCustomName() : Component.empty());
    }

    public Cat createCat(Level level) {
        var cat = EntityType.CAT.create(level);
        assert cat != null;
        cat.load(data);
        return cat;
    }
}
