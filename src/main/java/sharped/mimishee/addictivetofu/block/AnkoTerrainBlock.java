package sharped.mimishee.addictivetofu.block;

import baguchan.tofucraft.block.TofuTerrainBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class AnkoTerrainBlock extends TofuTerrainBlock {
    public AnkoTerrainBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState p_222508_, ServerLevel p_222509_, BlockPos p_222510_, RandomSource p_222511_) {
        if (!canBeGrass(p_222508_, p_222509_, p_222510_) && p_222508_.is(BlockRegister.TOFU_TERRAIN_REDBEAN.get())) {
            if (!p_222509_.isAreaLoaded(p_222510_, 1)) {
                return;
            }

            p_222509_.setBlockAndUpdate(p_222510_, TofuBlocks.TOFU_TERRAIN.get().defaultBlockState());
        }

    }
}
