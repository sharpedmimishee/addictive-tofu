package sharped.mimishee.addictivetofu.recipe;

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
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;

import java.util.List;

public record CompoundingCauldronRecipe(List<Ingredient> itemsInput, FluidIngredient fluidInput,
                                        ItemStack result) implements Recipe<CompoundingCauldronRecipeInput> {

    @Override
    public boolean matches(CompoundingCauldronRecipeInput input, Level level) {
        return this.itemsInput.get(0).test(input.getItem(0))
                && this.itemsInput.get(1).test(input.getItem(1))
                && this.itemsInput.get(2).test(input.getItem(2))
                && this.itemsInput.get(3).test(input.getItem(3))
                && this.itemsInput.get(4).test(input.getItem(4))
                && this.itemsInput.get(5).test(input.getItem(5))
                && this.itemsInput.get(6).test(input.getItem(6))
                && this.itemsInput.get(7).test(input.getItem(7))
                && this.itemsInput.get(8).test(input.getItem(8))
                && this.fluidInput.test(input.fluid());
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
        return Recipe.super.getIngredients();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
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
                ItemStack.CODEC.fieldOf("result").forGetter(CompoundingCauldronRecipe::result)
        ).apply(builder, CompoundingCauldronRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, CompoundingCauldronRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), CompoundingCauldronRecipe::itemsInput,
                        FluidIngredient.STREAM_CODEC, CompoundingCauldronRecipe::fluidInput,
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
