package sharped.mimishee.addictivetofu.client.animations;// Save this class in your mod and generate all required imports

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

/**
 * Made with Blockbench 4.12.4
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 *
 * @author Author
 */
public class AnkonianAnimations {
    public static final AnimationDefinition eat_right = AnimationDefinition.Builder.withLength(0.5F).looping()
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-112.7959F, -28.0243F, 11.1702F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25F, KeyframeAnimations.degreeVec(-87.7959F, -28.0243F, 11.1702F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-112.7959F, -28.0243F, 11.1702F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition baby = AnimationDefinition.Builder.withLength(0.0F)
            .addAnimation("root", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.6F, 0.6F, 0.6F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.3F, 1.3F, 1.3F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition eat_left = AnimationDefinition.Builder.withLength(0.5F).looping()
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-112.7959F, 28.0243F, -11.1702F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25F, KeyframeAnimations.degreeVec(-80.2959F, 28.0243F, -11.1702F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-112.7959F, 28.0243F, -11.1702F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition anger_right = AnimationDefinition.Builder.withLength(0.0F)
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-100.4332F, 17.8515F, -17.5252F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition anger_left = AnimationDefinition.Builder.withLength(0.0F)
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-119.944F, -11.3017F, 13.0467F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();
}