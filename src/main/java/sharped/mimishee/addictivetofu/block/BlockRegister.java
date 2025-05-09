package sharped.mimishee.addictivetofu.block;

import baguchan.tofucraft.block.utils.WeightBaseBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.block.tfenergy.TFPlantGrower;
import sharped.mimishee.addictivetofu.items.ItemRegister;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlockRegister {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AddictiveTofu.MODID);

    public static final DeferredBlock<Block> ADV_TOFU_BARREL = register("barrel_adv_tofu", () -> new WeightBaseBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> ADV_TOFU_BLOCK = register("adv_tofu_block", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.HEAVY_CORE)));

    public static final DeferredBlock<Block> REDBEAN_CROP = register("redbean_crop",
            () -> new RedBeanCropBlock(BlockBehaviour.Properties.of().noCollission()));

    public static final DeferredBlock<Block> MAGIC_BEAN = register("magicbean_crop",
            () -> new MagicBeanCropBlock(BlockBehaviour.Properties.of().noCollission()));
    public static final DeferredBlock<Block> TOFU_TERRAIN_REDBEAN = register("tofu_terrain_redbean",
            () -> new AnkoTerrainBlock(BlockBehaviour.Properties.of().strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_RED).randomTicks().sound(SoundType.SNOW)));

    public static final DeferredBlock<Block> COMPOUNDING_CAULDRON = register("compounding_cauldron",
            () -> new CompoundingCauldron(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<Block> TFPLANT_GROWER = register("tfplant_grower",
            () -> new TFPlantGrower(BlockBehaviour.Properties.of().noOcclusion()));

    private static <T extends Block> DeferredBlock<T> baseRegister(String name, Supplier<? extends T> block, Function<DeferredBlock<T>, Supplier<? extends Item>> item) {
        DeferredBlock<T> register = BLOCKS.register(name, block);
        Supplier<? extends Item> itemSupplier = item.apply(register);
        ItemRegister.ITEMS.register(name, itemSupplier);
        return register;
    }

    private static <T extends Block> DeferredBlock<T> noItemRegister(String name, Supplier<? extends T> block) {
        DeferredBlock<T> register = BLOCKS.register(name, block);
        return register;
    }

    private static <B extends Block> DeferredBlock<B> register(String name, Supplier<? extends Block> block) {
        return (DeferredBlock<B>) baseRegister(name, block, (object) -> BlockRegister.registerBlockItem(object));
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final Supplier<T> block) {
        return () -> {

            return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());

        };
    }
}
