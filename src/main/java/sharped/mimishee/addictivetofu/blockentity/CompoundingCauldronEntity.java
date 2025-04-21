package sharped.mimishee.addictivetofu.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import javax.annotation.Nullable;

public class CompoundingCauldronEntity extends BlockEntity {
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

    public boolean addItem(ItemStack stack) {
        for (var i=0;i < 9;i++) {
            if (this.items.getStackInSlot(i).isEmpty()) {
                this.items.insertItem(i, stack, false);
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
}
