package sharped.mimishee.addictivetofu.providers;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import sharped.mimishee.addictivetofu.AddictiveTofu;

public class BuiltInModLootTables {
    public static final ResourceKey<LootTable> TOFU_MANSION_SMITHING_ROOM = register("tofu_mansion/smithing_room");

    private static ResourceKey<LootTable> register(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, name));
    }
}
