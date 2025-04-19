package sharped.mimishee.addictivetofu.client.model;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import sharped.mimishee.addictivetofu.client.animations.CrimsonHunterAnimations;
import sharped.mimishee.addictivetofu.entity.CrimsonHunter;

public class CrimsonHunterModel<T extends CrimsonHunter> extends AnkonianModel<T> {
    private final ModelPart root;
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    private final ModelPart head;
    private final ModelPart hat;
    private final ModelPart body;
    private final ModelPart quiver;
    private final ModelPart right_arm;
    private final ModelPart left_arm;

    public CrimsonHunterModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
        this.head = this.root.getChild("head");
        this.hat = this.head.getChild("hat");
        this.body = this.root.getChild("body");
        this.quiver = this.body.getChild("quiver");
        this.right_arm = this.root.getChild("right_arm");
        this.left_arm = this.root.getChild("left_arm");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, 0.0F));

        PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, -10.0F, 0.0F));

        PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -10.0F, 0.0F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.5F, -11.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 0.0F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 16).addBox(-3.0F, -4.0F, -2.0F, 6.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 0.0F));

        PartDefinition quiver = body.addOrReplaceChild("quiver", CubeListBuilder.create().texOffs(36, 16).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

        PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(28, 16).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -17.5F, 0.0F));

        PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(28, 16).mirror().addBox(0.0F, -6.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -11.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if (entity.mesugakiAnimationState.isStarted() && entity.isAggressive()) {
            right_arm.resetPose();
            left_arm.resetPose();
            body.resetPose();
        }
        animate(entity.mesugakiAnimationState, CrimsonHunterAnimations.mesugaki, ageInTicks);
    }

    @Override
    public void translateToHead(ModelPart modelPart, PoseStack poseStack) {
        this.root.translateAndRotate(poseStack);
        modelPart.translateAndRotate(poseStack);
    }

    @Override
    public void translateToChest(ModelPart modelPart, PoseStack poseStack) {
        this.root.translateAndRotate(poseStack);
        modelPart.translateAndRotate(poseStack);
        poseStack.scale(0.75F, 0.95F, 0.75F);
    }

    @Override
    public void translateToLeg(ModelPart modelPart, PoseStack poseStack) {
        this.root.translateAndRotate(poseStack);
        modelPart.translateAndRotate(poseStack);
        poseStack.scale(0.5F, 0.95F, 0.5F);
    }

    @Override
    public void translateToChestPat(ModelPart modelPart, PoseStack poseStack) {
        this.root.translateAndRotate(poseStack);
        modelPart.translateAndRotate(poseStack);
        poseStack.scale(0.75F, 0.75F, 0.75F);
    }
}