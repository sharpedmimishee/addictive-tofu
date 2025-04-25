package sharped.mimishee.addictivetofu.providers;

import baguchan.tofucraft.data.generator.RegistryDataGenerator;
import net.minecraft.Util;
import net.minecraft.core.Cloner;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.RegistryPatchGenerator;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import sharped.mimishee.addictivetofu.AddictiveTofu;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = AddictiveTofu.MODID)
public class ModProviders {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        // Data generators may require some of these as constructor parameters.
        // See below for more details on each of these.
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();


        RegistrySetBuilder.PatchedRegistries tofuRegistries = RegistryPatchGenerator.createLookup(
                CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor()),
                RegistryDataGenerator.BUILDER
        ).join();

        //ここでtofu craftのdataに対応。build時にエラーを出さないように
        var lookup = createLookup(event.getLookupProvider(), tofuRegistries).thenApply(RegistrySetBuilder.PatchedRegistries::full);


        DatapackBuiltinEntriesProvider datapackProvider = new ModRegistryDataGenerator(output, lookup);
        generator.addProvider(event.includeServer(), datapackProvider);

        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
        // Register the provider.
        generator.addProvider(
                event.includeClient(),
                // Our provider.
                new BlockModels(output, existingFileHelper)
        );
        generator.addProvider(
                // A boolean that determines whether the data should actually be generated.
                // The event provides methods that determine this:
                // event.includeClient(), event.includeServer(),
                // event.includeDev() and event.includeReports().
                // Since recipes are server data, we only run them in a server datagen.
                event.includeClient(),
                // Our provider.
                new ItemModelsGenerator(output, existingFileHelper)
        );
        BlockTagGenerator blockTagGenerator = new BlockTagGenerator(output, lookupProvider, existingFileHelper);
        generator.addProvider(
                event.includeServer(),
                blockTagGenerator
        );
        generator.addProvider(
                event.includeServer(),
                new ItemTagGenerator(output, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper)
        );
        generator.addProvider(
                event.includeServer(),
                new BiomeTagGenerator(output, lookupProvider, existingFileHelper)
        );
        generator.addProvider(event.includeServer(), new ModRecipes(output, lookupProvider));
        generator.addProvider(
                event.includeServer(),
                ModLootTableProvider.create(output, lookupProvider)
        );
        generator.addProvider(
                event.includeServer(),
                new ModAdvancements(output, lookupProvider, existingFileHelper)
        );

    }

    public static CompletableFuture<RegistrySetBuilder.PatchedRegistries> createLookup(
            CompletableFuture<HolderLookup.Provider> lookup, RegistrySetBuilder.PatchedRegistries patchedRegistries
    ) {
        return lookup.thenApply(
                p_311522_ -> {
                    Cloner.Factory cloner$factory = new Cloner.Factory();
                    net.neoforged.neoforge.registries.DataPackRegistriesHooks.getDataPackRegistriesWithDimensions().forEach(p_311524_ -> p_311524_.runWithArguments(cloner$factory::addCodec));
                    RegistrySetBuilder.PatchedRegistries registrysetbuilder$patchedregistries = patchedRegistries;
                    HolderLookup.Provider holderlookup$provider = registrysetbuilder$patchedregistries.full();
                    Optional<HolderLookup.RegistryLookup<Biome>> optional = holderlookup$provider.lookup(Registries.BIOME);
                    Optional<HolderLookup.RegistryLookup<PlacedFeature>> optional1 = holderlookup$provider.lookup(Registries.PLACED_FEATURE);
                    if (optional.isPresent() || optional1.isPresent()) {
                        VanillaRegistries.validateThatAllBiomeFeaturesHaveBiomeFilter(
                                optional1.orElseGet(() -> p_311522_.lookupOrThrow(Registries.PLACED_FEATURE)),
                                optional.orElseGet(() -> p_311522_.lookupOrThrow(Registries.BIOME))
                        );
                    }

                    return registrysetbuilder$patchedregistries;
                }
        );
    }
}
