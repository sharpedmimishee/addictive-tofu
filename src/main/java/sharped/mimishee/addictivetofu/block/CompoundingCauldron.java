package sharped.mimishee.addictivetofu.block;

import baguchan.tofucraft.registry.TofuItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.blockentity.BlockEntityRegister;
import sharped.mimishee.addictivetofu.blockentity.CompoundingCauldronEntity;

public class CompoundingCauldron extends BaseEntityBlock {
    private static final Logger log = LoggerFactory.getLogger(CompoundingCauldron.class);

    public CompoundingCauldron(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    public static final MapCodec<CompoundingCauldron> CODEC = simpleCodec(CompoundingCauldron::new);
    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0);
    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new CompoundingCauldronEntity(p_153215_, p_153216_);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(level.getBlockEntity(pos) instanceof CompoundingCauldronEntity compoundingCauldronEntity) {
            compoundingCauldronEntity.dropAll();
            level.updateNeighbourForOutputSignal(pos, this);
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (CompoundingCauldronEntity.crafting) {
            double y = pos.getY()+0.5;
                level.addParticle(ParticleTypes.ENCHANT, pos.getX() + 1.5+0.5, y, pos.getZ()+0.5, -0.5, 0, 0);
            level.addParticle(ParticleTypes.ENCHANT, pos.getX() + 1.5+0.5, y, pos.getZ()+0.5, -0.5, 0, 0);
                level.addParticle(ParticleTypes.ENCHANT, pos.getX()+1.5+0.5, y, pos.getZ()+1.5+0.5, -0.5, 0, 0-0.5);
            level.addParticle(ParticleTypes.ENCHANT, pos.getX()+1.5+0.5, y, pos.getZ()+1.5+0.5, -0.5, 0, 0-0.5);
                level.addParticle(ParticleTypes.ENCHANT, pos.getX()+0.5, y, pos.getZ()+1.5+0.5, 0, 0, -0.5);
                level.addParticle(ParticleTypes.ENCHANT, pos.getX()-1.5+0.5, y, pos.getZ()+1.5+0.5, 0.5, 0, -0.5);
            level.addParticle(ParticleTypes.ENCHANT, pos.getX()-1.5+0.5, y, pos.getZ()+1.5+0.5, 0.5, 0, -0.5);
                level.addParticle(ParticleTypes.ENCHANT, pos.getX()-1.5+0.5, y, pos.getZ()+0.5, 0.5, 0, 0);
                level.addParticle(ParticleTypes.ENCHANT, pos.getX()-1.5+0.5, y, pos.getZ()-1.5+0.5, 0.5,  0, 0.5);
            level.addParticle(ParticleTypes.ENCHANT, pos.getX()-1.5+0.5, y, pos.getZ()-1.5+0.5, 0.5,  0, 0.5);
                level.addParticle(ParticleTypes.ENCHANT, pos.getX()+0.5, y, pos.getZ()-1.5+0.5, 0,  0, 0.5);
            level.addParticle(ParticleTypes.ENCHANT, pos.getX()+0.5, y, pos.getZ()-1.5+0.5, 0,  0, 0.5);
                level.addParticle(ParticleTypes.ENCHANT, pos.getX()+1.5+0.5, y, pos.getZ()-1.5+0.5, -0.5,  0, 0.5);
            level.addParticle(ParticleTypes.ENCHANT, pos.getX()+1.5+0.5, y, pos.getZ()-1.5+0.5, -0.5,  0, 0.5);
            for (var i=0;i < 10; i++) {
                level.addParticle(ParticleTypes.ENCHANT, pos.getX() + 0.5, y + 1.0, pos.getZ() + 0.5, -0.2, 0.2, -0.2);
            }
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, BlockEntityRegister.COMPOUNDING_CAULDRON_ENTITY.get(),
                (level1, blockPos, blockState, blockEntity) -> blockEntity.tick());
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.getBlockEntity(pos) instanceof CompoundingCauldronEntity compoundingCauldronEntity) {
            if (player.getMainHandItem().isEmpty() && player.isCrouching()) {
//                AddictiveTofu.LOGGER.info("JUST TAKE IT");
                player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(compoundingCauldronEntity.takeItem(), 1));
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
            } else {
                IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack.copyWithCount(1)).orElse(null);
                if (player.getMainHandItem().getItem() == TofuItems.TOFUSTICK.get()) {
//                    AddictiveTofu.LOGGER.info("you'd just used TofuStick on the block!");
                    if (compoundingCauldronEntity.hasRecipe()) {
//                        AddictiveTofu.LOGGER.info("hasRecipe!");
                        compoundingCauldronEntity.trueCraft();
                    }
                } else if (handler instanceof FluidBucketWrapper) {
                    if (compoundingCauldronEntity.addFluid(((FluidBucketWrapper) handler).getFluid())) {
                        level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1f, 1f);
                        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BUCKET, 1));
                    } else if (player.getMainHandItem().getItem() == Items.BUCKET) {
                        Fluid fluid = compoundingCauldronEntity.takeFluid();
                        level.playSound(player, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1f, 1f);
                        player.setItemInHand(InteractionHand.MAIN_HAND, fluid.getBucket().getDefaultInstance());
                    }
                } else {
                    Item item = stack.getItem();
                    ItemStack itemStack = new ItemStack(item, 1);
                    boolean result = compoundingCauldronEntity.addItem(itemStack);
                    if (result) {
                        stack.shrink(1);
                        level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
                    }
                }
            }
        }
        return ItemInteractionResult.SUCCESS;
    }


}
