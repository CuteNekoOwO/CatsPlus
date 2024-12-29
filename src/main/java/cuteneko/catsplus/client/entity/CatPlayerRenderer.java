package cuteneko.catsplus.client.entity;

import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.CatVariant;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class CatPlayerRenderer extends LivingEntityRenderer<AbstractClientPlayer, OcelotModel<AbstractClientPlayer>> {
    public CatPlayerRenderer(EntityRendererProvider.Context context) {
        super(context, new OcelotModel<>(context.bakeLayer(ModelLayers.CAT)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(AbstractClientPlayer entity) {
        return getVariant(entity.getUUID()).texture();
    }

    private CatVariant getVariant(UUID uuid) {
        return switch ((int) (uuid.getLeastSignificantBits() % 11)) {
            case 0 -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.TABBY);
            case 1 -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.BLACK);
            case 2 -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.RED);
            case 3 -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.SIAMESE);
            case 4 -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.BRITISH_SHORTHAIR);
            case 5 -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.CALICO);
            case 6 -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.PERSIAN);
            case 7 -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.RAGDOLL);
            case 8 -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.WHITE);
            case 9 -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.JELLIE);
//            case 10 -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.ALL_BLACK);
            default -> BuiltInRegistries.CAT_VARIANT.get(CatVariant.ALL_BLACK);
        };
    }
}
