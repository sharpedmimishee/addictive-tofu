package sharped.mimishee.addictivetofu.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeOuterLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Slime;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.client.ModModelLayers;
import sharped.mimishee.addictivetofu.client.model.AnkonianModel;
import sharped.mimishee.addictivetofu.entity.ZundaSlime;

public class ZundaSlimeRender extends MobRenderer<ZundaSlime, SlimeModel<ZundaSlime>> {
    private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, "textures/entity/slime/zunda_slime.png");
    public ZundaSlimeRender(EntityRendererProvider.Context p_174391_) {
        super(p_174391_, new SlimeModel(p_174391_.bakeLayer(ModelLayers.SLIME)), 0.5F);
        this.addLayer(new SlimeOuterLayer<>(this, p_174391_.getModelSet()));
    }

    public void render(ZundaSlime entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        this.shadowRadius = 0.25F * (float)entity.getSize();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    protected void scale(ZundaSlime livingEntity, PoseStack poseStack, float partialTickTime) {
        float f = 0.999F;
        poseStack.scale(0.999F, 0.999F, 0.999F);
        poseStack.translate(0.0F, 0.001F, 0.0F);
        float f1 = (float)livingEntity.getSize();
        float f2 = Mth.lerp(partialTickTime, livingEntity.oSquish, livingEntity.squish) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        poseStack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    public ResourceLocation getTextureLocation(ZundaSlime entity) {
        return LOCATION;
    }
}
