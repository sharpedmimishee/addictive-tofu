package sharped.mimishee.addictivetofu.providers;

import baguchan.tofucraft.data.generator.recipe.CraftingGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import sharped.mimishee.addictivetofu.AddictiveTofu;

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
        DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGenerator(output, event.getLookupProvider());

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
        generator.addProvider(event.includeServer(), new CraftingGenerator(output, lookupProvider));
        generator.addProvider(
                event.includeServer(),
                ModLootTableProvider.create(output, lookupProvider)
        );
        generator.addProvider(
                event.includeServer(),
                new ModAdvancements(output, lookupProvider, existingFileHelper)
        );

    }
}
