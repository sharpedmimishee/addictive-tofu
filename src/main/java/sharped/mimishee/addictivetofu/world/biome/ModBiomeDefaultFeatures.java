package sharped.mimishee.addictivetofu.world.biome;

import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import sharped.mimishee.addictivetofu.providers.register.ModConfiguredWorldCarvers;
import sharped.mimishee.addictivetofu.providers.register.ModTofuWorldPlacements;

public class ModBiomeDefaultFeatures {
    public static void addDefaultCarvers(BiomeGenerationSettings.Builder builder) {
        builder.addCarver(GenerationStep.Carving.AIR, ModConfiguredWorldCarvers.CAVE);
        builder.addCarver(GenerationStep.Carving.AIR, ModConfiguredWorldCarvers.CAVE_EXTRA_UNDERGROUND);
        builder.addCarver(GenerationStep.Carving.AIR, ModConfiguredWorldCarvers.CANYON);
    }

    public static void addDefaultOres(BiomeGenerationSettings.Builder p_194721_) {
        p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModTofuWorldPlacements.ORE_TOFU_DIAMOND);
        p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModTofuWorldPlacements.ORE_TOFU_DIAMOND_BURIED);
        p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModTofuWorldPlacements.ORE_TOFU_DIAMOND_LARGE);
        p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModTofuWorldPlacements.ORE_TOFUGEM);
        p_194721_.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModTofuWorldPlacements.ORE_TOFUGEM_LARGE);
    }
}
