package sharped.mimishee.addictivetofu.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.material.FluidState;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.blockentity.CompoundingCauldronEntity;

public class CompoundingCauldronRenderer implements BlockEntityRenderer<CompoundingCauldronEntity> {
    public CompoundingCauldronRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
        this.fluidrenderer = context.getBlockRenderDispatcher().getLiquidBlockRenderer();
    }
    private final ItemRenderer itemRenderer;
    private final LiquidBlockRenderer fluidrenderer;
    @Override
    public void render(CompoundingCauldronEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        int i = (int)blockEntity.getBlockPos().asLong();
        poseStack.translate(0.5F, 0.45F, 0.5F);
        for (int j = 0; j < blockEntity.itemStackHandler.getSlots(); j++) {
            ItemStack itemstack = blockEntity.itemStackHandler.getStackInSlot(j);
            if (itemstack != ItemStack.EMPTY) {
                poseStack.pushPose();
                switch (j) {
                    case 1: poseStack.translate(-0.04f, 0.0045f, 0.02f);
                    case 2: poseStack.translate(0.02f, 0.0062f, -0.02f);
                    case 3: poseStack.translate(-0.01f, 0.0058f, 0.01f);
                    case 4: poseStack.translate(0.03f, 0.0055f, 0.03f);
                    case 5: poseStack.translate(0.03f, 0.006f, -0.03f);
                    case 6: poseStack.translate(0.048f, 0.006f, 0.05f);
                    case 7: poseStack.translate(0.039f, 0.0059f, 0.01f);
                    case 8: poseStack.translate(-0.03f, 0.0055f, -0.02f);
                }
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.scale(0.375F, 0.375F, 0.375F);
                this.itemRenderer.renderStatic(itemstack,
                        ItemDisplayContext.FIXED, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                        packedOverlay, poseStack, bufferSource, blockEntity.getLevel(), i + j);
                poseStack.popPose();
            }
        }
//        if (!blockEntity.tank.isEmpty()) {
//            poseStack.translate(0.03, -0.07, 0.2);
//            this.fluidrenderer.tesselate(blockEntity.getLevel(), blockEntity.getBlockPos(), bufferSource.getBuffer(RenderType.SOLID), blockEntity.getBlockState(), blockEntity.getBlockState().getFluidState());
//            poseStack.popPose();
//        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}