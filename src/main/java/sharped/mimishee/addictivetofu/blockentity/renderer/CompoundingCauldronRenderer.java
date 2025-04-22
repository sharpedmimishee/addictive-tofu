package sharped.mimishee.addictivetofu.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.blockentity.CompoundingCauldronEntity;

public class CompoundingCauldronRenderer implements BlockEntityRenderer<CompoundingCauldronEntity> {
    public CompoundingCauldronRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(CompoundingCauldronEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        for (var i=0; i < 9; i++) {

            switch (i) {
                case 0: poseStack.translate(0.5f, 1.15f, 0.5f);
                case 1: poseStack.translate(0.38f, 0.6f, 0.4f);
                case 2: poseStack.translate(0.35f, 0.62f, 0.5f);
                case 3: poseStack.translate(0.42f, 0.58f, 0.39f);
                case 4: poseStack.translate(0.34f, 0.55f, 0.67f);
                case 5: poseStack.translate(0.29f, 0.6f, 0.32f);
                case 6: poseStack.translate(0.72f, 0.6f, 0.70f);
                case 7: poseStack.translate(0.32f, 0.59f, 0.65f);
                case 8: poseStack.translate(0.67f, 0.55f, 0.62f);
            }
            ItemStack stack = blockEntity.items.getStackInSlot(i);

            poseStack.pushPose();
            poseStack.scale(0.5f, 0.5f, 0.5f);
            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, getLightLevel(blockEntity.getLevel(),
                    blockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, poseStack, bufferSource, blockEntity.getLevel(), 1);
            poseStack.mulPose(Axis.YN.rotationDegrees(90f));
            poseStack.popPose();
        }

    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}