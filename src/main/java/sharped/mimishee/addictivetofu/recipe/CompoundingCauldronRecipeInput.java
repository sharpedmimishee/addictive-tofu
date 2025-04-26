package sharped.mimishee.addictivetofu.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record CompoundingCauldronRecipeInput(List<ItemStack> items, FluidStack fluid) implements RecipeInput {
    // Method to get an item from a specific slot. We have one stack and no concept of slots, so we just assume
    // that slot 0 holds our item, and throw on any other slot. (Taken from SingleRecipeInput#getItem.)
    @Override
    public @NotNull ItemStack getItem(int slot) {
//        if (slot != 0) throw new IllegalArgumentException("No item for index " + slot);
        return items.get(slot);
    }

    @Override
    public FluidStack fluid() {
        return fluid;
    }

    @Override
    public List<ItemStack> items() {
        return items;
    }

    // The slot size our input requires. Again, we don't really have a concept of slots, so we just return 1
    // because we have one item stack involved. Inputs with multiple items should return the actual count here.
    @Override
    public int size() {
        return 9;
    }
}
