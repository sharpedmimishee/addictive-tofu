package sharped.mimishee.addictivetofu.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.recipe.CompoundingCauldronRecipe;
import sharped.mimishee.addictivetofu.recipe.CompoundingCauldronRecipeInput;
import sharped.mimishee.addictivetofu.recipe.RecipeRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompoundingCauldronEntity extends BlockEntity implements Container {
    public final ItemStackHandler itemStackHandler = new ItemStackHandler(9) {
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return super.getStackLimit(slot, stack);
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    public final FluidTank fluidTank = new FluidTank(1000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (level != null)
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    };

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 32;
    public static boolean crafting = false;
    public static int end_crafting = 0;

    public CompoundingCauldronEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegister.COMPOUNDING_CAULDRON_ENTITY.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CompoundingCauldronEntity.this.progress;
                    case 1 -> CompoundingCauldronEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CompoundingCauldronEntity.this.progress = value;
                    case 1 -> CompoundingCauldronEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemStackHandler.serializeNBT(pRegistries));
        pTag.put("fluidTank", fluidTank.writeToNBT(pRegistries, new CompoundTag()));
        pTag.putInt("compoundingcauldron.progress", progress);
        pTag.putInt("compoundingcauldron.max_progress", maxProgress);
        pTag.putBoolean("compoudningcauldron.crafting", crafting);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemStackHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        fluidTank.readFromNBT(pRegistries, pTag.getCompound("fluidTank"));
        progress = pTag.getInt("compoundingcauldron.progress");
        maxProgress = pTag.getInt("compoundingcauldron.max_progress");
        crafting = pTag.getBoolean("compoudningcauldron.crafting");
    }

    @Override
    public int getContainerSize() {
        return 9;
    }

    @Override
    public boolean isEmpty() {
        for (var i=0; i < this.itemStackHandler.getSlots(); i++) {
            if (!this.itemStackHandler.getStackInSlot(i).isEmpty())
                return false;
        };
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.itemStackHandler.getStackInSlot(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return this.itemStackHandler.extractItem(slot, amount, false);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return this.itemStackHandler.extractItem(slot, this.itemStackHandler.getStackInSlot(slot).getCount(), false);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.itemStackHandler.insertItem(slot, stack, false);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public void dropAll() {
        SimpleContainer container = new SimpleContainer(itemStackHandler.getSlots());
        for (var i=0; i < this.itemStackHandler.getSlots(); i++) {
            ItemStack item = this.itemStackHandler.getStackInSlot(i);
            container.setItem(i, item);
        }
        Containers.dropContents(this.level, this.getBlockPos(), container);
    }
    public boolean addItem(ItemStack item) {
        for (var i=0; i < this.itemStackHandler.getSlots(); i++) {
            if (this.itemStackHandler.getStackInSlot(i).isEmpty()) {
                this.itemStackHandler.insertItem(i, item, false);
                return true;
            }
        }
        return false;
    }

    public boolean addFluid(FluidStack fluid) {
        if (this.fluidTank.isEmpty()) {
            this.fluidTank.fill(new FluidStack(fluid.getFluid(), 1000), IFluidHandler.FluidAction.EXECUTE);
            return true;
        }
        return false;
    }

    public Item takeItem() {
        for (var i=0; i < this.itemStackHandler.getSlots(); i++) {
            if (!this.itemStackHandler.getStackInSlot(i).isEmpty()) {
                Item item = this.itemStackHandler.getStackInSlot(i).getItem().asItem();
                this.itemStackHandler.extractItem(i, 1, false);
                return item;
            }
        }
        return ItemStack.EMPTY.getItem();
    }

    public Fluid takeFluid() {
        if (!this.fluidTank.isEmpty()) {
            Fluid fluid = this.fluidTank.getFluid().getFluid();
            this.fluidTank.setFluid(FluidStack.EMPTY);
            return fluid;
        }
        return FluidStack.EMPTY.getFluid();
    }

    @Override
    public void clearContent() {

    }

    private Optional<RecipeHolder<CompoundingCauldronRecipe>> getCurrentRecipe() {
        List<ItemStack> stack = new ArrayList<>();
        for (var i=0; i < this.itemStackHandler.getSlots(); i++) {
//            AddictiveTofu.LOGGER.info(this.itemStackHandler.getStackInSlot(i).toString());
            if (!this.itemStackHandler.getStackInSlot(i).isEmpty()) {
                stack.add(this.itemStackHandler.getStackInSlot(i));
            } else {
                stack.add(ItemStack.EMPTY);
            }
        }
        if (this.fluidTank.getFluid().getAmount() == 1000) {
            return this.level.getRecipeManager()
                    .getRecipeFor(RecipeRegister.COMPOUNDING_CAULDRON_TYPE.get(), new CompoundingCauldronRecipeInput(stack, this.fluidTank.getFluid()), level);
        }
        return Optional.empty();
    }

    public boolean hasRecipe() {
        Optional<RecipeHolder<CompoundingCauldronRecipe>> recipe = getCurrentRecipe();
        if (recipe.isPresent()) {
            this.maxProgress = recipe.get().value().compoundingTime();
            return true;
        } else { return false; }
    }

    public void toggleCraft() {
        crafting = !crafting;
    }

    public void trueCraft() {crafting = true;}

    public void increaseProgress() {
        if (this.progress+1 <= this.maxProgress) {
            this.progress = this.progress + 1;
        }
        AddictiveTofu.LOGGER.info(String.valueOf(this.progress));
    }
    public void tick() {
        if (this.progress == this.maxProgress) {
            this.progress = 0;
            this.maxProgress = 32;
            craftItem();
        } else if (crafting) {
            increaseProgress();
        }
    }


    public void craftItem() {
//        SimpleContainer container = new SimpleContainer();
        if (getCurrentRecipe().isPresent()) {
            ItemStack item = getCurrentRecipe().get().value().result();
//        AddictiveTofu.LOGGER.info(item.toString());
//        container.addItem(item);
            for (var i = 0; i < this.itemStackHandler.getSlots(); i++) {
                this.itemStackHandler.extractItem(i, 1, false);
                this.itemStackHandler.insertItem(i, ItemStack.EMPTY, false);
            }
            this.fluidTank.setFluid(FluidStack.EMPTY);
            setChanged();
            if (level != null && !level.isClientSide) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
//            AddictiveTofu.LOGGER.info(String.valueOf(this.fluidTank.isEmpty()));
            this.itemStackHandler.insertItem(0, item, false);
//        Containers.dropContents(this.level, this.getBlockPos(), container);
        }
        toggleCraft();
        end_crafting = 5;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    public ContainerData getData() {
        return data;
    }
}
