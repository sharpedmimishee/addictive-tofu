package sharped.mimishee.addictivetofu.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import sharped.mimishee.addictivetofu.AddictiveTofu;

public class ModTags {
    public static class EntityType {
        public static final TagKey<net.minecraft.world.entity.EntityType<?>> ANKONIAN_FRIENDS = tag("ankonian_friends");

        private static TagKey<net.minecraft.world.entity.EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, name));
        }
    }
}
