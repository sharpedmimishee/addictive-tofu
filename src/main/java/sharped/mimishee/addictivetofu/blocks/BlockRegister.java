package sharped.mimishee.addictivetofu.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.items.ItemRegister;

import java.util.function.Supplier;

public class BlockRegister {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AddictiveTofu.MODID);

    public static final DeferredBlock<Block> REDBEAN_CROP = registerBlock("redbean_crop",
            () -> new RedBeanCropBlock(BlockBehaviour.Properties.of().destroyTime(1f)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> Block = BLOCKS.register(name, block);
        ItemRegister.ITEMS.register(name, () -> new BlockItem(Block.get(), new Item.Properties()));
        return Block;
    }
    public static void register(IEventBus modEventbus) {
        BLOCKS.register(modEventbus);
    }
}
