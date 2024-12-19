package cuteneko.catsplus.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.time.OffsetDateTime;

public record CatSpirit(OffsetDateTime time, Component reason) {
    public static final Codec<CatSpirit> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("time").forGetter(CatSpirit::getTime),
                    ComponentSerialization.CODEC.fieldOf("reason").forGetter(CatSpirit::reason)
            ).apply(instance, CatSpirit::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CatSpirit> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, CatSpirit::getTime,
            ComponentSerialization.STREAM_CODEC, CatSpirit::reason,
            CatSpirit::new);

    public CatSpirit(String time, Component reason) {
        this(OffsetDateTime.parse(time), reason);
    }

    public String getTime() {
        return time.toString();
    }
}
