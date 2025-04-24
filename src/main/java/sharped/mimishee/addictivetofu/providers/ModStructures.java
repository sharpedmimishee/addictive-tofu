package sharped.mimishee.addictivetofu.providers;

import baguchan.tofucraft.TofuCraftReload;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.register.ModTags;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModStructures {
    public static final ResourceKey<Structure> TOFU_MANSION = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_mansion"));

    public static final ResourceKey<StructureSet> TOFU_MANSION_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_mansion"));

    public static final ResourceKey<StructureTemplatePool> TOFU_MANSION_CORE = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_mansion/core"));
    public static final ResourceKey<StructureTemplatePool> TOFU_MANSION_FLOOR_1 = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_mansion/floor_1/14x14"));
    public static final ResourceKey<StructureTemplatePool> TOFU_MANSION_ANKONIAN = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_mansion/mob/ankonian"));
    public static final ResourceKey<StructureTemplatePool> TOFU_MANSION_CRIMSON_HUNTER = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_mansion/mob/crimson_hunter"));
    public static final ResourceKey<StructureTemplatePool> TOFU_MANSION_TOFUNIAN = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_mansion/mob/tofunian"));


    public static void bootstrapStructures(BootstrapContext<Structure> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

        context.register(
                TOFU_MANSION,
                new JigsawStructure(
                        new Structure.StructureSettings.Builder(biomes.getOrThrow(ModTags.Biomes.TOFU_MANSION))
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
                                .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                                .build(),
                        pools.getOrThrow(TOFU_MANSION_CORE),
                        Optional.empty(),
                        6,
                        ConstantHeight.of(VerticalAnchor.absolute(0)),
                        false,
                        Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                        80,
                        List.of(),
                        JigsawStructure.DEFAULT_DIMENSION_PADDING,
                        JigsawStructure.DEFAULT_LIQUID_SETTINGS
                )
        );
    }

    public static void bootstrapSets(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
        context.register(TOFU_MANSION_SET, new StructureSet(structures.getOrThrow(TOFU_MANSION), new RandomSpreadStructurePlacement(34, 6, RandomSpreadType.LINEAR, 16324620)));
    }

    public static void bootstrapPools(BootstrapContext<StructureTemplatePool> context) {
        Holder<StructureTemplatePool> emptyPool = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);
        HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);

        context.register(TOFU_MANSION_CORE, new StructureTemplatePool(emptyPool, ImmutableList.of(
                Pair.of(StructurePoolElement.single(name("tofu_mansion/core")), 1)
        ), StructureTemplatePool.Projection.RIGID));
        context.register(TOFU_MANSION_FLOOR_1, new StructureTemplatePool(emptyPool, ImmutableList.of(
                Pair.of(StructurePoolElement.single(name("tofu_mansion/floor/room_14x14_1")), 1),
                Pair.of(StructurePoolElement.single(name("tofu_mansion/floor/room_14x14_2")), 1),
                Pair.of(StructurePoolElement.single(name("tofu_mansion/floor/room_14x14_3")), 1)
        ), StructureTemplatePool.Projection.RIGID));
        context.register(TOFU_MANSION_ANKONIAN, new StructureTemplatePool(emptyPool, ImmutableList.of(
                Pair.of(StructurePoolElement.single(name("tofu_mansion/mob/ankonian")), 1)
        ), StructureTemplatePool.Projection.RIGID));
        context.register(TOFU_MANSION_CRIMSON_HUNTER, new StructureTemplatePool(emptyPool, ImmutableList.of(
                Pair.of(StructurePoolElement.single(name("tofu_mansion/mob/crimson_hunter")), 1)
        ), StructureTemplatePool.Projection.RIGID));
        context.register(TOFU_MANSION_TOFUNIAN, new StructureTemplatePool(emptyPool, ImmutableList.of(
                Pair.of(StructurePoolElement.single(name("tofu_mansion/mob/tofunian")), 1)
        ), StructureTemplatePool.Projection.RIGID));
    }

    public static void bootstrapProcessors(BootstrapContext<StructureProcessorList> context) {
    }

    private static String name(String name) {
        return ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, name).toString();
    }
}