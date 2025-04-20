package sharped.mimishee.addictivetofu.providers.register;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import sharped.mimishee.addictivetofu.AddictiveTofu;

import static baguchan.tofucraft.world.gen.features.TofuWorldFeatures.TOFUSLATE_ORE_REPLACEABLES;
import static baguchan.tofucraft.world.gen.features.TofuWorldFeatures.TOFU_ORE_REPLACEABLES;

public class ModConfiguredFeatures {
    public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_DIAMOND_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFU_DIAMOND.get().defaultBlockState()), OreConfiguration.target(TOFUSLATE_ORE_REPLACEABLES, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_TOFUGEM_TARGET_LIST = ImmutableList.of(OreConfiguration.target(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFUGEM.get().defaultBlockState()));


    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_SMALL = registerKey("ore_tofu_diamond_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_LARGE = registerKey("ore_tofu_diamond_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_BURIED = registerKey("ore_tofu_diamond_buried");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TOFUGEM_SMALL = registerKey("ore_tofugem_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TOFUGEM_LARGE = registerKey("ore_tofugem_large");

    private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
        return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest ruletest = new TagMatchTest(TofuTags.Blocks.TOFU_TERRAIN);

        FeatureUtils.register(context, ORE_DIAMOND_SMALL, Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 5, 0.2F));
        FeatureUtils.register(context, ORE_DIAMOND_LARGE, Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 12, 0.5F));
        FeatureUtils.register(context, ORE_DIAMOND_BURIED, Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 8, 1.0F));

        FeatureUtils.register(context, ORE_TOFUGEM_SMALL, Feature.ORE, new OreConfiguration(ORE_TOFUGEM_TARGET_LIST, 6));
        FeatureUtils.register(context, ORE_TOFUGEM_LARGE, Feature.ORE, new OreConfiguration(ORE_TOFUGEM_TARGET_LIST, 10));

    }
}