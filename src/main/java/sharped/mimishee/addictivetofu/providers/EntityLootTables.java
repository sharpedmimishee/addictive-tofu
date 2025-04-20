package sharped.mimishee.addictivetofu.providers;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import sharped.mimishee.addictivetofu.entity.EntityRegister;
import sharped.mimishee.addictivetofu.items.ItemRegister;

import java.util.stream.Stream;

import static sharped.mimishee.addictivetofu.entity.EntityRegister.ENTITIES;

public class EntityLootTables extends EntityLootSubProvider {
    protected EntityLootTables(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        ENTITIES.getEntries().stream()
//                .filter(entity -> entity.get().lootTable == null)
                .forEach(entity -> this.add(entity.get(), LootTable.lootTable()));

        this.add(
                EntityRegister.CRIMSON_HUNTER.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(TofuItems.ZUNDARUBY.get())
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 2.0F)))
                                        )
                        ).withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(
                                        LootItem.lootTableItem(ItemRegister.REDBEAN_PASTE)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                ))
        );
        this.add(
                EntityRegister.ANKONIAN.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(TofuItems.ZUNDARUBY.get())
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 2.0F)))
                                        )
                        ).withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(
                                        LootItem.lootTableItem(ItemRegister.REDBEAN_PASTE)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                ))
        );
        this.add(
                EntityRegister.REDBEAN_SLIME.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(2.0F))
                                        .add(
                                                LootItem.lootTableItem(ItemRegister.REDBEAN)
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 2.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(ItemRegister.REDBEAN_PASTE)
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        )
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
        );
        this.add(
                EntityRegister.ZUNDA_SLIME.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(TofuItems.ZUNDA.get())
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        )
                        )
        );

    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ENTITIES.getEntries()
                .stream().map(DeferredHolder::get);
    }
}
