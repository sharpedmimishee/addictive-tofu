package sharped.mimishee.addictivetofu.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import sharped.mimishee.addictivetofu.AddictiveTofu;

import java.util.ArrayList;
import java.util.List;

public record CompoundingCauldronRecipe(List<Ingredient> itemsInput, FluidIngredient fluidInput,
                                        Integer compoundingTime, ItemStack result) implements Recipe<CompoundingCauldronRecipeInput> {

    @Override
    public boolean matches(CompoundingCauldronRecipeInput input, Level level) {
        List<ItemStack> items = new ArrayList<>();
        for (var i=0;i < this.itemsInput.size();i++) {items.add(input.getItem(i));}

        if (items.size() != this.itemsInput.size()) return false;
        for (var i=0;i < this.itemsInput.size(); i++) {
            for (var j=0;j < items.size();j++) {
                if (this.itemsInput.get(i).test(items.get(j))) {
                    items.remove(j);
                    break;
                }
            }
        }
        return items.isEmpty();
    }

    @Override
    public ItemStack assemble(CompoundingCauldronRecipeInput input, HolderLookup.Provider registries) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (var i=0;i < itemsInput().size();i++) {
            ingredients.add(itemsInput.get(i));
        }
        return ingredients;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    public FluidStack getFluidInput() {
        return this.fluidInput.getStacks()[0];
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.COMPOUNDING_CAULDRON_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegister.COMPOUNDING_CAULDRON_TYPE.get();
    }

    public static class CompoundingCauldronSerializer implements RecipeSerializer<CompoundingCauldronRecipe> {
        public static final MapCodec<CompoundingCauldronRecipe> CODEC = RecordCodecBuilder.mapCodec((builder) -> builder.group(
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("itemInput").forGetter(CompoundingCauldronRecipe::itemsInput),
                FluidIngredient.CODEC_NON_EMPTY.fieldOf("fluidInput").forGetter(CompoundingCauldronRecipe::fluidInput),
                Codec.INT.optionalFieldOf("tick", 64).forGetter(CompoundingCauldronRecipe::compoundingTime),
                ItemStack.CODEC.fieldOf("result").forGetter(CompoundingCauldronRecipe::result)
        ).apply(builder, CompoundingCauldronRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, CompoundingCauldronRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), CompoundingCauldronRecipe::itemsInput,
                        FluidIngredient.STREAM_CODEC, CompoundingCauldronRecipe::fluidInput,
                        ByteBufCodecs.fromCodec(Codec.INT), CompoundingCauldronRecipe::compoundingTime,
                        ItemStack.STREAM_CODEC, CompoundingCauldronRecipe::result,
                        CompoundingCauldronRecipe::new);

        @Override
        public MapCodec<CompoundingCauldronRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CompoundingCauldronRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
