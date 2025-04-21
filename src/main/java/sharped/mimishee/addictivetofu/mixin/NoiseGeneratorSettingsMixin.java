package sharped.mimishee.addictivetofu.mixin;

import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sharped.mimishee.addictivetofu.api.ITofuRule;

import javax.annotation.Nullable;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin implements ITofuRule {

    @Shadow
    @Final
    private SurfaceRules.RuleSource surfaceRule;
    @Unique
    @Nullable
    private SurfaceRules.RuleSource tofuRule;

    @Unique
    @Nullable
    public SurfaceRules.RuleSource addictive_tofu$getTofuRule() {
        return tofuRule;
    }

    @Unique
    public void addictive_tofu$setTofuRule(@Nullable SurfaceRules.RuleSource tofuRule) {
        this.tofuRule = tofuRule;
    }

    @Inject(method = "surfaceRule", at = @At("HEAD"), cancellable = true)
    private void surfaceRule(CallbackInfoReturnable<SurfaceRules.RuleSource> cir) {
        if (this.tofuRule != null) {

            cir.setReturnValue(this.tofuRule);
        }
    }
}
