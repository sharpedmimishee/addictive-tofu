package sharped.mimishee.addictivetofu.providers.register;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.world.biome.ModBiomeBuilders;

public class ModBiomes {
    public static final ResourceKey<Biome> REDBEAN_FOREST = register("redbean_forest");

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> vanillaConfiguredCarvers = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(REDBEAN_FOREST, ModBiomeBuilders.redbeanForestBiome(placedFeatures, vanillaConfiguredCarvers));
    }

    private static ResourceKey<Biome> register(String p_48229_) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, p_48229_));
    }
}
