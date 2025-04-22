package sharped.mimishee.addictivetofu.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class CompoundingCauldronEntity extends BlockEntity implements Container {
    public CompoundingCauldronEntity( BlockPos pos, BlockState blockState) {
        super(BlockEntityRegister.COMPOUNDING_CAULDRON_ENTITY.get(), pos, blockState);
    }
    public final ItemStackHandler items = new ItemStackHandler(9) {
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };


    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
//        var nbt = new CompoundTag();
        pTag.put("inventory", items.serializeNBT(pRegistries));
//        pTag.putInt("growth_chamber.progress", progress);
//        pTag.putInt("growth_chamber.max_progress", maxProgress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        items.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
//        progress = pTag.getInt("growth_chamber.progress");
//        maxProgress = pTag.getInt("growth_chamber.max_progress");
    }

    public ItemStackHandler getItems() {
        return this.items;
    }

    public boolean addItem(ItemStack stack) {
        SimpleContainer inventory = new SimpleContainer(items.getSlots());
        for (var i=0;i < 9;i++) {
            if (this.items.getStackInSlot(i).isEmpty()) {
                this.items.insertItem(i, stack, false);
                inventory.setItem(i, stack);
                return true;
            }
        }
        return false;
    }

    public Item takeItem() {
        for (var i=0;i < 9;i++) {
            ItemStack stack = this.items.getStackInSlot(i);
            if (!stack.isEmpty()) {
                this.items.extractItem(i, 1, false);
                return stack.getItem();
            }
        }
        return ItemStack.EMPTY.getItem();
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

    @Override
    public int getContainerSize() {
        return 9;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.items.getStackInSlot(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return this.items.extractItem(slot, amount, false);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return null;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {

    }
}
