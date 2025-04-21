package sharped.mimishee.addictivetofu.api;

import net.minecraft.world.level.levelgen.SurfaceRules;

import javax.annotation.Nullable;

public interface ITofuRule {
    @Nullable
    SurfaceRules.RuleSource addictive_tofu$getTofuRule();

    void addictive_tofu$setTofuRule(@Nullable SurfaceRules.RuleSource tofuRule);
}
