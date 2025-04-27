package sharped.mimishee.addictivetofu.providers;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import sharped.mimishee.addictivetofu.AddictiveTofu;

public class BuiltInModLootTables {
    public static final ResourceKey<LootTable> TOFU_MANSION_SMITHING_ITEM = register("tofu_mansion/smithing_room");
    public static final ResourceKey<LootTable> TOFU_MANSION_FOOD_ITEM = register("tofu_mansion/food_room");
    public static final ResourceKey<LootTable> TOFU_MANSION_MISC_ITEM = register("tofu_mansion/misc_room");
    public static final ResourceKey<LootTable> TOFU_MANSION_CHEST_ITEM = register("tofu_mansion/main_chest");
    public static final ResourceKey<LootTable> TOFU_MANSION_LIBRARY_ITEM = register("tofu_mansion/library");

    private static ResourceKey<LootTable> register(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, name));
    }
}
