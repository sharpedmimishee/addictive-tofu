package sharped.mimishee.addictivetofu.providers.register;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import sharped.mimishee.addictivetofu.AddictiveTofu;

import java.util.List;

public class ModTofuWorldPlacements {

    public static final ResourceKey<PlacedFeature> ORE_TOFU_DIAMOND = registerKey("ore_tofu_diamond");
    public static final ResourceKey<PlacedFeature> ORE_TOFU_DIAMOND_LARGE = registerKey("ore_tofu_diamond_large");
    public static final ResourceKey<PlacedFeature> ORE_TOFU_DIAMOND_BURIED = registerKey("ore_tofu_diamond_buried");
    public static final ResourceKey<PlacedFeature> ORE_TOFUGEM = registerKey("ore_tofugem");
    public static final ResourceKey<PlacedFeature> ORE_TOFUGEM_LARGE = registerKey("ore_tofugem_large");

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, name));
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(context, ORE_TOFU_DIAMOND, configuredFeature.getOrThrow(ModConfiguredFeatures.ORE_DIAMOND_SMALL), commonOrePlacement(25, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        PlacementUtils.register(context, ORE_TOFU_DIAMOND_LARGE, configuredFeature.getOrThrow(ModConfiguredFeatures.ORE_DIAMOND_LARGE), rareOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        PlacementUtils.register(context, ORE_TOFU_DIAMOND_BURIED, configuredFeature.getOrThrow(ModConfiguredFeatures.ORE_DIAMOND_BURIED), commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        PlacementUtils.register(context, ORE_TOFUGEM, configuredFeature.getOrThrow(ModConfiguredFeatures.ORE_TOFUGEM_SMALL), commonOrePlacement(15, HeightRangePlacement.uniform(VerticalAnchor.absolute(136), VerticalAnchor.top())));
        PlacementUtils.register(context, ORE_TOFUGEM_LARGE, configuredFeature.getOrThrow(ModConfiguredFeatures.ORE_TOFUGEM_LARGE), commonOrePlacement(22, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60))));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }
}