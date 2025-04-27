package sharped.mimishee.addictivetofu.providers;

import baguchan.tofucraft.registry.TofuStructures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.register.ModTags;
import sharped.mimishee.addictivetofu.world.gen.structure.mansion.TofuMansionStructure;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModStructures {
    public static final ResourceKey<Structure> TOFU_MANSION = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, "tofu_mansion"));

    public static final ResourceKey<StructureSet> TOFU_MANSION_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, "tofu_mansion"));


    private static ResourceKey<StructureProcessorList> createKey(String name) {
        return ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, name));
    }

    public static void bootstrapStructures(BootstrapContext<Structure> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

        context.register(
                TOFU_MANSION,
                new TofuMansionStructure(new Structure.StructureSettings.Builder(biomes.getOrThrow(ModTags.Biomes.TOFU_MANSION))
                        .spawnOverrides(
                                Arrays.stream(MobCategory.values())
                                        .collect(
                                                Collectors.toMap(
                                                        p_236555_ -> (MobCategory) p_236555_,
                                                        p_236551_ -> new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, WeightedRandomList.create())
                                                )
                                        )
                        )
                        .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                        .terrainAdapation(TerrainAdjustment.NONE)
                        .build())
        );
    }

    public static void bootstrapSets(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
        HolderGetter<StructureSet> structureSets = context.lookup(Registries.STRUCTURE_SET);
        context.register(TOFU_MANSION_SET, new StructureSet(structures.getOrThrow(TOFU_MANSION), new RandomSpreadStructurePlacement(Vec3i.ZERO,
                StructurePlacement.FrequencyReductionMethod.DEFAULT,
                1F, 18431620, Optional.of(new StructurePlacement.ExclusionZone(structureSets.getOrThrow(TofuStructures.TOFU_CASTLE_SET), 5)),
                32, 6, RandomSpreadType.LINEAR)));
    }

    public static void bootstrapPools(BootstrapContext<StructureTemplatePool> context) {
    }

    public static void bootstrapProcessors(BootstrapContext<StructureProcessorList> context) {
    }

    private static void register(
            BootstrapContext<StructureProcessorList> context, ResourceKey<StructureProcessorList> key, List<StructureProcessor> processors
    ) {
        context.register(key, new StructureProcessorList(processors));
    }

    private static String name(String name) {
        return ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, name).toString();
    }
}