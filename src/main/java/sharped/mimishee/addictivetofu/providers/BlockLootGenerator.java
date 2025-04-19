package sharped.mimishee.addictivetofu.providers;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import sharped.mimishee.addictivetofu.blocks.RedBeanCropBlock;
import sharped.mimishee.addictivetofu.blocks.BlockRegister;
import sharped.mimishee.addictivetofu.items.ItemRegister;

import java.util.Set;

public class BlockLootGenerator extends BlockLootSubProvider {
    protected BlockLootGenerator(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }
    protected LootTable.Builder createDrops(Block block, Item item, float min, float max) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }
    @Override
    protected void generate() {
        LootItemCondition.Builder lootItemBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(BlockRegister.REDBEAN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RedBeanCropBlock.AGE, 4));
        add(BlockRegister.REDBEAN_CROP.get(), block -> createCropDrops(BlockRegister.REDBEAN_CROP.get(),
                ItemRegister.REDBEAN.get(), ItemRegister.REDBEAN.get(), lootItemBuilder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegister.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
