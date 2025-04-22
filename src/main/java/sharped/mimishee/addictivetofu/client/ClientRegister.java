package sharped.mimishee.addictivetofu.client;

import baguchan.tofucraft.registry.TofuDataComponents;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.blockentity.BlockEntityRegister;
import sharped.mimishee.addictivetofu.blockentity.renderer.CompoundingCauldronRenderer;
import sharped.mimishee.addictivetofu.client.model.AnkonianModel;
import sharped.mimishee.addictivetofu.client.model.CrimsonHunterModel;
import sharped.mimishee.addictivetofu.client.render.AnkonianRender;
import sharped.mimishee.addictivetofu.client.render.CrimsonHunterRender;
import sharped.mimishee.addictivetofu.client.render.RedBeanSlimeRender;
import sharped.mimishee.addictivetofu.client.render.ZundaSlimeRender;
import sharped.mimishee.addictivetofu.entity.EntityRegister;
import sharped.mimishee.addictivetofu.items.ItemRegister;
import sharped.mimishee.addictivetofu.items.ZundaCrossbowItem;

@EventBusSubscriber(modid = AddictiveTofu.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegister {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegister.COMPOUNDING_CAULDRON_ENTITY.get(),
                // Pass the context to an empty (default) constructor call
                CompoundingCauldronRenderer::new
        );
    }
    @SubscribeEvent
    public static void modelBake(ModelEvent.ModifyBakingResult event) {
        ItemProperties.register(
                ItemRegister.TF_SWORD.asItem(),
                ResourceLocation.withDefaultNamespace("charged"),
                (p_275891_, p_275892_, p_275893_, p_275894_) -> p_275891_.has(TofuDataComponents.TF_ENERGY_DATA) && p_275891_.get(TofuDataComponents.TF_ENERGY_DATA).storeTF() > 0 ? 1.0F : 0.0F
        );

        ItemProperties.register(
                ItemRegister.ZUNDA_CROSSBOW.asItem(),
                ResourceLocation.withDefaultNamespace("pull"),
                (p_351682_, p_351683_, p_351684_, p_351685_) -> {
                    if (p_351684_ == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(p_351682_)
                                ? 0.0F
                                : (float) (p_351682_.getUseDuration(p_351684_) - p_351684_.getUseItemRemainingTicks())
                                / (float) ZundaCrossbowItem.getChargeDuration(p_351682_, p_351684_);
                    }
                }
        );
        ItemProperties.register(
                ItemRegister.ZUNDA_CROSSBOW.asItem(),
                ResourceLocation.withDefaultNamespace("pulling"),
                (p_174605_, p_174606_, p_174607_, p_174608_) -> p_174607_ != null
                        && p_174607_.isUsingItem()
                        && p_174607_.getUseItem() == p_174605_
                        && !CrossbowItem.isCharged(p_174605_)
                        ? 1.0F
                        : 0.0F
        );
        ItemProperties.register(
                ItemRegister.ZUNDA_CROSSBOW.asItem(),
                ResourceLocation.withDefaultNamespace("charged"),
                (p_275891_, p_275892_, p_275893_, p_275894_) -> CrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F
        );
        ItemProperties.register(ItemRegister.ZUNDA_CROSSBOW.asItem(), ResourceLocation.withDefaultNamespace("firework"), (p_329796_, p_329797_, p_329798_, p_329799_) -> {
            ChargedProjectiles chargedprojectiles = p_329796_.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
        ItemProperties.register(ItemRegister.ZUNDA_CROSSBOW.asItem(), ResourceLocation.withDefaultNamespace("zunda_arrow"), (p_329796_, p_329797_, p_329798_, p_329799_) -> {
            ChargedProjectiles chargedprojectiles = p_329796_.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(TofuItems.ZUNDA_ARROW.get()) ? 1.0F : 0.0F;
        });

        ItemProperties.register(ItemRegister.ANKO_BOW.asItem(), ResourceLocation.withDefaultNamespace("pull"), (p_344163_, p_344164_, p_344165_, p_344166_) -> {
            if (p_344165_ == null) {
                return 0.0F;
            } else {
                return p_344165_.getUseItem() != p_344163_ ? 0.0F : (float) (p_344163_.getUseDuration(p_344165_) - p_344165_.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(
                ItemRegister.ANKO_BOW.asItem(),
                ResourceLocation.withDefaultNamespace("pulling"),
                (p_174630_, p_174631_, p_174632_, p_174633_) -> p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F
        );
    }

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegister.ANKONIAN.get(), AnkonianRender::new);
        event.registerEntityRenderer(EntityRegister.CRIMSON_HUNTER.get(), CrimsonHunterRender::new);
        event.registerEntityRenderer(EntityRegister.ZUNDA_SLIME.get(), ZundaSlimeRender::new);
        event.registerEntityRenderer(EntityRegister.REDBEAN_SLIME.get(), RedBeanSlimeRender::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ANKONIAN, AnkonianModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CRIMSON_HUNTER, CrimsonHunterModel::createBodyLayer);
//        event.registerLayerDefinition();
    }
}
