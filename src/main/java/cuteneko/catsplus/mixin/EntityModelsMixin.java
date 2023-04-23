package cuteneko.catsplus.mixin;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModels;
import org.spongepowered.asm.mixin.Mixin;

@Environment(value= EnvType.CLIENT)
@Mixin(EntityModels.class)
public class EntityModelsMixin {
//    @Inject(method = "getModels", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
//    private static void onBuild(CallbackInfoReturnable<Map<EntityModelLayer, TexturedModelData>> cir, ImmutableMap.Builder<EntityModelLayer, TexturedModelData> builder) {
//        builder.put(EntityModelLayers.PLAYER, TexturedModelData.of(OcelotEntityModel.getModelData(Dilation.NONE), 64, 64));
//        cir.setReturnValue(builder.buildKeepingLast());
//    }
}
