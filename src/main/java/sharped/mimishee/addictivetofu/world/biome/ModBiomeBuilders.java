package sharped.mimishee.addictivetofu.world.biome;

import baguchan.tofucraft.registry.TofuSounds;
import baguchan.tofucraft.world.biome.TofuBiomeDefaultFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomeBuilders {
    public static Biome redbeanForestBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
        TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
        return makeDefaultBiome(builder, builder1, TofuSounds.ROUGH_GROUND_BGM);
    }

    public static Biome makeDefaultBiome(BiomeGenerationSettings.Builder builder, MobSpawnSettings.Builder mobSpawnSetting, Holder<SoundEvent> soundEvent) {
        ModBiomeDefaultFeatures.addDefaultCarvers(builder);
        ModBiomeDefaultFeatures.addDefaultOres(builder);
        TofuBiomeDefaultFeatures.tofuCreatureSpawns(mobSpawnSetting);
        return fullDefinition(Biome.Precipitation.NONE, 0.8F, 0.6F, (new BiomeSpecialEffects.Builder()).fogColor(9671612).skyColor(16777215).waterColor(12311280).waterFogColor(6330816).grassColorOverride(7115607).foliageColorOverride(7115607).grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE).backgroundMusic(new Music(soundEvent, 12000, 24000, false)).build(), mobSpawnSetting.build(), builder.build(), Biome.TemperatureModifier.NONE);
    }

    public static Biome fullDefinition(Biome.Precipitation precipitation, float temperature, float downfall, BiomeSpecialEffects effects, MobSpawnSettings spawnSettings, BiomeGenerationSettings generationSettings, Biome.TemperatureModifier temperatureModifier) {
        return (new Biome.BiomeBuilder()).temperature(temperature).downfall(downfall).specialEffects(effects).mobSpawnSettings(spawnSettings).generationSettings(generationSettings).temperatureAdjustment(temperatureModifier).build();
    }
}
