package sharped.mimishee.addictivetofu.providers;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public record ChestLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(
                BuiltInModLootTables.TOFU_MANSION_SMITHING_ROOM,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(10.0F))
                                        .add(LootItem.lootTableItem(TofuItems.TOFUGEM.get()).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFUDIAMOND.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFUMETAL.get()).setWeight(30).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_AXE.get()).setWeight(5).apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_PICKAXE.get()).setWeight(5).apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_SHOVEL.get()).setWeight(5).apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)))
                                        .add(EmptyLootItem.emptyItem().setWeight(5))
                        ));
    }
}
