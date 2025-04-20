package sharped.mimishee.addictivetofu.mixin;

import baguchan.tofucraft.registry.TofuDimensions;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.WorldStem;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.register.ModParameterPoints;

import java.util.ArrayList;
import java.util.List;

@Mixin(WorldStem.class)
public abstract class WorldStemMixin {
    //? if >=1.19.4 {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onStemCreated(CloseableResourceManager closeableResourceManager, ReloadableServerResources reloadableServerResources, LayeredRegistryAccess<RegistryLayer> layeredRegistryAccess, WorldData worldData, CallbackInfo ci) {
        var registryAccess = layeredRegistryAccess.compositeAccess();
        doReplacement(registryAccess.registryOrThrow(Registries.BIOME), registryAccess.registryOrThrow(Registries.LEVEL_STEM));
    }


    @Unique
    private void doReplacement(Registry<Biome> biomeRegistry, Registry<LevelStem> stemRegistry) {
        for (var entry : stemRegistry.entrySet()) {
            var levelId = entry.getKey().location();
            var level = entry.getValue();

            if (levelId == TofuDimensions.tofu_world_stem.location() && level.generator() instanceof NoiseBasedChunkGenerator generator
                    && generator.getBiomeSource() instanceof MultiNoiseBiomeSource) {
                var biomeSource = (MultiNoiseBiomeSourceAccessor) generator.getBiomeSource();

                var parameters = biomeSource.getParameters().map((p) -> p, (holder) -> holder.value().parameters());
                List<Pair<Climate.ParameterPoint, Holder<Biome>>> newParameterList = new ArrayList<>();

                newParameterList.addAll(parameters.values());
                newParameterList.add(new Pair<>(ModParameterPoints.TEST, biomeRegistry.getHolderOrThrow(Biomes.DEEP_DARK)));

                biomeSource.setParameters(Either.left(new Climate.ParameterList<>(newParameterList)));

                AddictiveTofu.LOGGER.info("Successfully add biomes in " + levelId);
            } else AddictiveTofu.LOGGER.info("Skipping " + levelId);
        }
    }
}