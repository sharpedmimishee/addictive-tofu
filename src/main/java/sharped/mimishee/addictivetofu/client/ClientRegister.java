package sharped.mimishee.addictivetofu.client;

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
import net.neoforged.neoforge.client.event.ModelEvent;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.items.ItemRegister;
import sharped.mimishee.addictivetofu.items.ZundaCrossbowItem;

@EventBusSubscriber(modid = AddictiveTofu.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegister {
    @SubscribeEvent
    public static void modelBake(ModelEvent.ModifyBakingResult event) {
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
    }
}
