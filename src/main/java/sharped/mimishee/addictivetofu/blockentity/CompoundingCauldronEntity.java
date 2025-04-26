package sharped.mimishee.addictivetofu.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
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
        }
    };

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 64;



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

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemStackHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        fluidTank.readFromNBT(pRegistries, pTag.getCompound("fluidTank"));
        progress = pTag.getInt("compoundingcauldron.progress");
        maxProgress = pTag.getInt("compoundingcauldron.max_progress");
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
        for (var i=0; i > this.itemStackHandler.getSlots(); i++) {
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
            AddictiveTofu.LOGGER.info(this.itemStackHandler.getStackInSlot(i).toString());
            if (!this.itemStackHandler.getStackInSlot(i).isEmpty()) {
                stack.add(this.itemStackHandler.getStackInSlot(i));
            } else {
                stack.add(ItemStack.EMPTY);
            }
        }
        AddictiveTofu.LOGGER.info(this.fluidTank.getFluid().toString());
        return this.level.getRecipeManager()
                .getRecipeFor(RecipeRegister.COMPOUNDING_CAULDRON_TYPE.get(), new CompoundingCauldronRecipeInput(stack, this.fluidTank.getFluid()), level);
    }

    public boolean hasRecipe() {
        Optional<RecipeHolder<CompoundingCauldronRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

//        ItemStack output = recipe.get().value().getResult();
        return true;
    }

    public void craftItem() {
        SimpleContainer container = new SimpleContainer();
        ItemStack item = getCurrentRecipe().get().value().result();
        AddictiveTofu.LOGGER.info(item.toString());
        container.addItem(item);
        for (var i=0; i < this.itemStackHandler.getSlots();i++) {
            this.itemStackHandler.extractItem(i, 1, false);
        }
        this.fluidTank.drain(this.fluidTank.getFluid(), IFluidHandler.FluidAction.EXECUTE);
//        this.itemStackHandler.insertItem(1, item, false);
        Containers.dropContents(this.level, this.getBlockPos(), container);
    }
}
