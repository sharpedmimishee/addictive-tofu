package sharped.mimishee.addictivetofu.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import sharped.mimishee.addictivetofu.items.ItemRegister;

public class RedBeanCropBlock extends CropBlock {
    public RedBeanCropBlock(Properties properties) {
        super(properties);
    }
    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0 ,4);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
    };
    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 4;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ItemRegister.REDBEAN;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(4) != 0) {
            super.randomTick(state, level, pos, random);
        }
    }

    @Override
    protected int getBonemealAgeIncrease(Level level) {
        return super.getBonemealAgeIncrease(level) / 4;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }
}
