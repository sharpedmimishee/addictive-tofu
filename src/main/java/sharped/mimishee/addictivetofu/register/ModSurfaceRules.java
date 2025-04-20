package sharped.mimishee.addictivetofu.register;

import baguchan.tofucraft.registry.TofuBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import sharped.mimishee.addictivetofu.block.BlockRegister;
import sharped.mimishee.addictivetofu.providers.register.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource TOFU_TERRAIN = makeStateRule(TofuBlocks.TOFU_TERRAIN.get());
    private static final SurfaceRules.RuleSource TOFU_TERRAIN_REDBEAN = makeStateRule(BlockRegister.TOFU_TERRAIN_REDBEAN.get());

    public static SurfaceRules.RuleSource revampedTofuWorldLike(SurfaceRules.RuleSource surfaceRules) {
        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();

        SurfaceRules.RuleSource surface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), TOFU_TERRAIN_REDBEAN), TOFU_TERRAIN);
        SurfaceRules.RuleSource overworldLike = SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.REDBEAN_FOREST), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, surface), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, TOFU_TERRAIN)));
        SurfaceRules.RuleSource surfacerules$rulesource9 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), overworldLike);
        builder.add(surfacerules$rulesource9);
        return SurfaceRules.sequence(surfaceRules, SurfaceRules.sequence(builder.build().toArray((p_198379_) -> new SurfaceRules.RuleSource[p_198379_])));
    }

    private static SurfaceRules.RuleSource makeStateRule(Block p_194811_) {
        return SurfaceRules.state(p_194811_.defaultBlockState());
    }

}
