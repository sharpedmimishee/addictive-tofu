package sharped.mimishee.addictivetofu.providers;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuEnchantments;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import sharped.mimishee.addictivetofu.items.ItemRegister;

import java.util.function.BiConsumer;

public record ChestLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        output.accept(
                BuiltInModLootTables.TOFU_MANSION_SMITHING_ITEM,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(4, 10.0F))
                                        .add(LootItem.lootTableItem(TofuItems.TOFUGEM.get()).setWeight(30).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFUDIAMOND.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFUMETAL.get()).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_AXE.get()).setWeight(5).apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_PICKAXE.get()).setWeight(5).apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_SHOVEL.get()).setWeight(5).apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_CHESTPLATE.get()).setWeight(4).apply(EnchantWithLevelsFunction.enchantWithLevels(this.registries, UniformGenerator.between(5, 20))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_LEGGINGS.get()).setWeight(4).apply(EnchantWithLevelsFunction.enchantWithLevels(this.registries, UniformGenerator.between(5, 20))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_HELMET.get()).setWeight(4).apply(EnchantWithLevelsFunction.enchantWithLevels(this.registries, UniformGenerator.between(5, 20))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_BOOTS.get()).setWeight(4).apply(EnchantWithLevelsFunction.enchantWithLevels(this.registries, UniformGenerator.between(5, 20))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_DIAMOND_HELMET.get()).setWeight(1).apply(EnchantWithLevelsFunction.enchantWithLevels(this.registries, UniformGenerator.between(5, 15))))

                        ));
        output.accept(
                BuiltInModLootTables.TOFU_MANSION_FOOD_ITEM,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(4, 6.0F))
                                        .add(LootItem.lootTableItem(ItemRegister.REDBEAN.get()).setWeight(30).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuBlocks.SOYCHEESE_TART).setWeight(5))
                                        .add(LootItem.lootTableItem(TofuItems.TOFUCOOKIE.get()).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_HAMBURG.get()).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.SOYMEAT.get()).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFUISHI.get()).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFUKINU.get()).setWeight(25).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        ));
        output.accept(
                BuiltInModLootTables.TOFU_MANSION_LIBRARY_ITEM,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(4, 6.0F))
                                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(Items.PAPER).setWeight(30).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(1).apply(new EnchantRandomlyFunction.Builder().withEnchantment(registrylookup.getOrThrow(TofuEnchantments.BATCH))))
                                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(5).apply(new EnchantRandomlyFunction.Builder().withEnchantment(registrylookup.getOrThrow(TofuEnchantments.DRAIN))))
                                        .add(EmptyLootItem.emptyItem().setWeight(2))
                        ));

        output.accept(
                BuiltInModLootTables.TOFU_MANSION_MISC_ITEM,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(1, 2.0F))
                                        .add(NestedLootTable.lootTableReference(BuiltInModLootTables.TOFU_MANSION_FOOD_ITEM))
                                        .add(NestedLootTable.lootTableReference(BuiltInModLootTables.TOFU_MANSION_SMITHING_ITEM))
                                        .add(NestedLootTable.lootTableReference(BuiltInModLootTables.TOFU_MANSION_LIBRARY_ITEM))
                                        .add(EmptyLootItem.emptyItem().setWeight(5))
                        ));
        output.accept(
                BuiltInModLootTables.TOFU_MANSION_CHEST_ITEM,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(8, 10.0F))
                                        .add(LootItem.lootTableItem(ItemRegister.REDBEAN.get()).setWeight(30).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_AXE.get()).setWeight(5).apply(EnchantRandomlyFunction.randomEnchantment()))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_METAL_HELMET.get()).setWeight(5).apply(EnchantRandomlyFunction.randomEnchantment()))
                                        .add(LootItem.lootTableItem(ItemRegister.ANKO_BOW.get()).setWeight(10).apply(EnchantRandomlyFunction.randomEnchantment()))
                                        .add(LootItem.lootTableItem(TofuBlocks.DIAMONDTOFU.get()).setWeight(2))
                                        .add(LootItem.lootTableItem(TofuBlocks.METALTOFU.get()).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                        .add(LootItem.lootTableItem(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                        ));
    }
}
