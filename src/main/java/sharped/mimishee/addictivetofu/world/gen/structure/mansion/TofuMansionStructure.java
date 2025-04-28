package sharped.mimishee.addictivetofu.world.gen.structure.mansion;

import baguchan.tofucraft.registry.TofuBlocks;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import sharped.mimishee.addictivetofu.register.ModStructureTypes;

import java.util.List;
import java.util.Optional;

public class TofuMansionStructure extends Structure {
    public static final MapCodec<TofuMansionStructure> CODEC = simpleCodec(TofuMansionStructure::new);

    public TofuMansionStructure(Structure.StructureSettings settings) {
        super(settings);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        Rotation rotation = Rotation.getRandom(context.random());
        BlockPos blockpos = this.getLowestYIn5by5BoxOffset7Blocks(context, rotation);
        return blockpos.getY() < 60
                ? Optional.empty()
                : Optional.of(new Structure.GenerationStub(blockpos, p_230240_ -> this.generatePieces(p_230240_, context, blockpos, rotation)));
    }

    private void generatePieces(StructurePiecesBuilder builder, Structure.GenerationContext context, BlockPos pos, Rotation rotation) {
        List<TofuMansionPieces.WoodlandMansionPiece> list = Lists.newLinkedList();
        TofuMansionPieces.generateMansion(context.structureTemplateManager(), pos, rotation, list, context.random());
        list.forEach(builder::addPiece);
    }

    @Override
    public void afterPlace(
            WorldGenLevel level,
            StructureManager structureManager,
            ChunkGenerator chunkGenerator,
            RandomSource random,
            BoundingBox boundingBox,
            ChunkPos chunkPos,
            PiecesContainer pieces
    ) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int i = level.getMinBuildHeight();
        BoundingBox boundingbox = pieces.calculateBoundingBox();
        int j = boundingbox.minY();

        for (int k = boundingBox.minX(); k <= boundingBox.maxX(); k++) {
            for (int l = boundingBox.minZ(); l <= boundingBox.maxZ(); l++) {
                blockpos$mutableblockpos.set(k, j, l);
                if (!level.isEmptyBlock(blockpos$mutableblockpos)
                        && boundingbox.isInside(blockpos$mutableblockpos)
                        && pieces.isInsidePiece(blockpos$mutableblockpos)) {
                    for (int i1 = j - 1; i1 > i; i1--) {
                        blockpos$mutableblockpos.setY(i1);
                        if (!level.isEmptyBlock(blockpos$mutableblockpos) && !level.getBlockState(blockpos$mutableblockpos).liquid()) {
                            break;
                        }

                        level.setBlock(blockpos$mutableblockpos, TofuBlocks.ISHITOFU_SMOOTH_BRICK.get().defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    @Override
    public StructureType<?> type() {
        return ModStructureTypes.TOFU_MANSION.get();
    }
}
