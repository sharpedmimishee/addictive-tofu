package sharped.mimishee.addictivetofu.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.blockentity.CompoundingCauldronEntity;

import java.util.Objects;

public class CompoundingCauldronRenderer implements BlockEntityRenderer<CompoundingCauldronEntity> {
    public CompoundingCauldronRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
//        this.fluidrenderer = context.getBlockRenderDispatcher().getLiquidBlockRenderer();
    }
    private final ItemRenderer itemRenderer;
//    private final LiquidBlockRenderer fluidrenderer;
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
        if (!blockEntity.fluidTank.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.03, -0.07, 0.2);
            FluidStack fluid = blockEntity.getFluid();
            FluidState fluidState = blockEntity.fluidTank.getFluid().getFluid().defaultFluidState();
            IClientFluidTypeExtensions fluidTypeExtensions = IClientFluidTypeExtensions.of(fluid.getFluid());
            ResourceLocation stillTexture = fluidTypeExtensions.getStillTexture(fluid);
            FluidState state = fluid.getFluid().defaultFluidState();

            AddictiveTofu.LOGGER.info(stillTexture.getPath());
            AddictiveTofu.LOGGER.info(state.toString());

            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(stillTexture);
            int tintColor = fluidTypeExtensions.getTintColor(state, blockEntity.getLevel(), blockEntity.getBlockPos());

            AddictiveTofu.LOGGER.info(sprite.toString());
            AddictiveTofu.LOGGER.info(String.valueOf(tintColor));
//            float height = (((float) blockEntity.fluidTank.getFluidAmount() / blockEntity.fluidTank.getCapacity()) * 0.625f) + 0.25f;

            float height = 0.50f;
            VertexConsumer builder = bufferSource.getBuffer(ItemBlockRenderTypes.getRenderLayer(state));

            drawQuad(builder, poseStack, -0.42f, height, -0.52f, 0.34f, height, 0.22f, sprite.getU0(), sprite.getV0(), sprite.getU1(), sprite.getV1(), packedLight, -1);
//            this.fluidrenderer.tesselate(Objects.requireNonNull(blockEntity.getLevel()), blockEntity.getBlockPos(), bufferSource.getBuffer(RenderType.SOLID), blockEntity.getBlockState(), blockEntity.getBlockState().getFluidState());
            poseStack.popPose();
        }
    }
    private static void drawVertex(VertexConsumer builder, PoseStack poseStack, float x, float y, float z, float u, float v, int packedLight, int color) {
        builder.addVertex(poseStack.last().pose(), x, y, z)
                .setColor(color)
                .setUv(u, v)
                .setUv2(packedLight, packedLight)
                .setNormal(1, 0, 0);
    }

    private static void drawQuad(VertexConsumer builder, PoseStack poseStack, float x0, float y0, float z0, float x1, float y1, float z1, float u0, float v0, float u1, float v1, int packedLight, int color) {
        drawVertex(builder, poseStack, x0, y0, z0, u0, v0, packedLight, color);
        drawVertex(builder, poseStack, x0, y1, z1, u0, v1, packedLight, color);
        drawVertex(builder, poseStack, x1, y1, z1, u1, v1, packedLight, color);
        drawVertex(builder, poseStack, x1, y0, z0, u1, v0, packedLight, color);
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}