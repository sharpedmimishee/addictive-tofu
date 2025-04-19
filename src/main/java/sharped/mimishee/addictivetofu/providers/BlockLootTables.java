package sharped.mimishee.addictivetofu.providers;

import baguchan.tofucraft.block.utils.WeightBaseBlock;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import sharped.mimishee.addictivetofu.block.BlockRegister;
import sharped.mimishee.addictivetofu.block.RedBeanCropBlock;
import sharped.mimishee.addictivetofu.items.ItemRegister;

import java.util.HashSet;
import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {
    private final Set<Block> knownBlocks = new HashSet<>();
    // [VanillaCopy] super
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    private static final Set<Item> EXPLOSION_RESISTANT = Set.of();


    protected BlockLootTables(HolderLookup.Provider p_344943_) {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), p_344943_);
    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        super.add(block, builder);
        knownBlocks.add(block);
    }

    @Override
    protected void generate() {
        LootItemCondition.Builder tofugemAdvBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(BlockRegister.ADV_TOFU_BARREL.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

        add(BlockRegister.ADV_TOFU_BARREL.get(), applyExplosionDecay(BlockRegister.ADV_TOFU_BARREL.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.ADVANCE_TOFUGEM.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(ItemRegister.ADV_TOFU.get()).when(tofugemAdvBuilder).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F)))))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Blocks.BARREL).when(tofugemAdvBuilder).when(ExplosionCondition.survivesExplosion()).otherwise(LootItem.lootTableItem(BlockRegister.ADV_TOFU_BARREL.get()).when(ExplosionCondition.survivesExplosion())))));

        this.add(BlockRegister.ADV_TOFU_BLOCK.get(), createSingleItemTable(BlockRegister.ADV_TOFU_BLOCK.get()));

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(BlockRegister.REDBEAN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RedBeanCropBlock.AGE, 4));

        this.add(BlockRegister.REDBEAN_CROP.get(), this.createCropDrops(BlockRegister.REDBEAN_CROP.get(),
                ItemRegister.REDBEAN.get(), ItemRegister.REDBEAN.get(), lootItemConditionBuilder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return knownBlocks;
    }
}
