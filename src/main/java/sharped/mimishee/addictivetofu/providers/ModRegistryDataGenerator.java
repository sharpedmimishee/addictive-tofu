package sharped.mimishee.addictivetofu.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.providers.register.ModBiomes;
import sharped.mimishee.addictivetofu.providers.register.ModConfiguredFeatures;
import sharped.mimishee.addictivetofu.providers.register.ModConfiguredWorldCarvers;
import sharped.mimishee.addictivetofu.providers.register.ModTofuWorldPlacements;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModRegistryDataGenerator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.NOISE, (context) -> {
            })
            .add(Registries.DENSITY_FUNCTION, (context) -> {
            })
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModTofuWorldPlacements::bootstrap)
            .add(Registries.PROCESSOR_LIST, (context) -> {
            })
            .add(Registries.STRUCTURE, (context) -> {
            })
            .add(Registries.STRUCTURE_SET, (context) -> {
            })
            .add(Registries.TEMPLATE_POOL, (context) -> {
            })
            .add(Registries.CONFIGURED_CARVER, ModConfiguredWorldCarvers::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrap);


    public ModRegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of("minecraft", AddictiveTofu.MODID));
    }

}