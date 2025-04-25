package sharped.mimishee.addictivetofu.providers.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import sharped.mimishee.addictivetofu.AddictiveTofu;

public class ModConfiguredWorldCarvers {

    private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String p_256085_) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, p_256085_));
    }

    public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> p_255626_) {
    }
}