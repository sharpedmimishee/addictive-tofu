package sharped.mimishee.addictivetofu.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import sharped.mimishee.addictivetofu.AddictiveTofu;

import javax.annotation.Nullable;

import static sharped.mimishee.addictivetofu.blockentity.BlockEntityRegister.COMPOUNDING_CAULDRON_ENTITY;

public class CompoundingCauldronEntity extends BaseContainerBlockEntity {
    // The container size. This can of course be any value you want.
    public static final int SIZE = 9;
    // Our item stack list. This is not final due to #setItems existing.
    private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);

    public CompoundingCauldronEntity(BlockPos pos, BlockState blockState) {
        super(COMPOUNDING_CAULDRON_ENTITY.get(), pos, blockState);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.addictivetofu.compoudingcauldron");
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    public boolean addItem(ItemStack item) {
        for (var i=0; i < 9; i++) {
            if (this.items.get(i).isEmpty()) {
                this.items.set(i, item);
                return true;
            }
        }
        return false;
    }

    public Item takeItem() {
        for (var i=0; i < 9; i++) {
            if (this.items.get(i).getCount() >= 1) {
                Item item = this.items.get(i).getItem();
                this.items.set(i, ItemStack.EMPTY);
                return item;
            }
        }
        return Items.AIR;
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return null;
    }

    @Override
    public int getContainerSize() {
        return SIZE;
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
