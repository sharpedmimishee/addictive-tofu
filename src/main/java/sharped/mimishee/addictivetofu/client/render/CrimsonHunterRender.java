package sharped.mimishee.addictivetofu.client.render;

import bagu_chan.bagus_lib.client.layer.CustomArmorLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.client.ModModelLayers;
import sharped.mimishee.addictivetofu.client.model.CrimsonHunterModel;
import sharped.mimishee.addictivetofu.entity.CrimsonHunter;

public class CrimsonHunterRender extends MobRenderer<CrimsonHunter, CrimsonHunterModel<CrimsonHunter>> {
    private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, "textures/entity/ankonian/ankonian_hunter.png");

    public CrimsonHunterRender(EntityRendererProvider.Context p_173956_) {
        super(p_173956_, new CrimsonHunterModel(p_173956_.bakeLayer(ModModelLayers.CRIMSON_HUNTER)), 0.5F);
        this.addLayer(new CustomArmorLayer(this, p_173956_));
        this.addLayer(new ItemInHandLayer(this, p_173956_.getItemInHandRenderer()));
    }


    @Override
    public ResourceLocation getTextureLocation(CrimsonHunter entity) {
        return LOCATION;
    }
}
