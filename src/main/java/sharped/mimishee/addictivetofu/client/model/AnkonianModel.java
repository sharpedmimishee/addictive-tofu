package sharped.mimishee.addictivetofu.client.model;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import bagu_chan.bagus_lib.client.layer.IArmor;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import sharped.mimishee.addictivetofu.client.animations.AnkonianAnimations;
import sharped.mimishee.addictivetofu.entity.AbstractAnkonian;
import sharped.mimishee.addictivetofu.items.ItemRegister;

public class AnkonianModel<T extends AbstractAnkonian> extends HierarchicalModel<T> implements ArmedModel, HeadedModel, IArmor {
    private final ModelPart root;
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    private final ModelPart head;
    private final ModelPart hat;
    private final ModelPart body;
    private final ModelPart right_arm;
    private final ModelPart left_arm;

    public AnkonianModel(ModelPart root) {
        this.root = root.getChild("root");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
        this.head = this.root.getChild("head");
        this.hat = this.head.getChild("hat");
        this.body = this.root.getChild("body");
        this.right_arm = this.root.getChild("right_arm");
        this.left_arm = this.root.getChild("left_arm");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, 0.0F));

        PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, -6.0F, 0.0F));

        PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -6.0F, 0.0F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.5F, -11.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 16).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(28, 16).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -11.5F, 0.0F));

        PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(28, 16).mirror().addBox(0.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -11.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);

        this.head.zRot = 0.0F;


        if (this.riding) {
            this.right_arm.xRot = (-(float) Math.PI / 5F);
            this.left_arm.xRot = (-(float) Math.PI / 5F);
            this.right_leg.xRot = -1.4137167F;
            this.left_leg.xRot = -1.4137167F;
        } else {
            this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
            this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
            this.right_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
            this.left_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        }

        if (this.attackTime > 0.0F) {
            if (entity.getMainArm() == HumanoidArm.RIGHT) {
                this.right_arm.xRot = this.attackTime * -0.75F;
                this.right_arm.zRot = this.attackTime * -0.5F;
            } else {
                this.right_arm.xRot = this.attackTime * -0.75F;
                this.right_arm.zRot = this.attackTime * 0.5F;
            }
        } else if (entity.isAggressive()) {
            right_arm.resetPose();
            left_arm.resetPose();
            if (entity.isHolding(ItemRegister.ANKO_BOW.asItem())) {
                if (entity.getMainArm() == HumanoidArm.RIGHT) {
                    this.right_arm.yRot = -0.1F + this.head.yRot;
                    this.left_arm.yRot = 0.1F + this.head.yRot + 0.4F;
                    this.right_arm.xRot = (float) (-Math.PI / 2) + this.head.xRot;
                    this.left_arm.xRot = (float) (-Math.PI / 2) + this.head.xRot;
                } else {
                    this.right_arm.yRot = -0.1F + this.head.yRot - 0.4F;
                    this.left_arm.yRot = 0.1F + this.head.yRot;
                    this.right_arm.xRot = (float) (-Math.PI / 2) + this.head.xRot;
                    this.left_arm.xRot = (float) (-Math.PI / 2) + this.head.xRot;
                }
            } else {
                if (entity.getMainArm() == HumanoidArm.RIGHT) {
                    this.applyStatic(AnkonianAnimations.anger_right);
                } else {
                    this.applyStatic(AnkonianAnimations.anger_left);
                }
            }
        }
    }


    @Override
    public ModelPart root() {
        return this.root;
    }

    private ModelPart getArm(HumanoidArm p_102923_) {
        return p_102923_ == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    @Override
    public void translateToHand(HumanoidArm p_102925_, PoseStack p_102926_) {
        if (!this.young) {
            this.root.translateAndRotate(p_102926_);
            this.getArm(p_102925_).translateAndRotate(p_102926_);
            p_102926_.translate((double) 0.0F, -0.15, (double) 0.0F);
            p_102926_.scale(0.95F, 0.95F, 0.95F);
        }

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
        poseStack.scale(0.75F, 0.75F, 0.75F);
    }

    @Override
    public void translateToLeg(ModelPart modelPart, PoseStack poseStack) {
        this.root.translateAndRotate(poseStack);
        modelPart.translateAndRotate(poseStack);
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }

    @Override
    public void translateToChestPat(ModelPart modelPart, PoseStack poseStack) {
        this.root.translateAndRotate(poseStack);
        modelPart.translateAndRotate(poseStack);
        poseStack.scale(0.75F, 0.75F, 0.75F);
    }

    @Override
    public Iterable<ModelPart> rightHandArmors() {
        return ImmutableList.of(this.right_arm);
    }

    @Override
    public Iterable<ModelPart> leftHandArmors() {
        return ImmutableList.of(this.left_arm);
    }

    @Override
    public Iterable<ModelPart> rightLegPartArmors() {
        return ImmutableList.of(this.right_leg);
    }

    @Override
    public Iterable<ModelPart> leftLegPartArmors() {
        return ImmutableList.of(this.left_leg);
    }

    @Override
    public Iterable<ModelPart> bodyPartArmors() {
        return ImmutableList.of(this.body);
    }

    @Override
    public Iterable<ModelPart> headPartArmors() {
        return ImmutableList.of(this.head);
    }
}