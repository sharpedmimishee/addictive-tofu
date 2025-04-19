package sharped.mimishee.addictivetofu.providers;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import sharped.mimishee.addictivetofu.block.BlockRegister;

import java.util.concurrent.CompletableFuture;

public class ModRecipes extends RecipeProvider {
    public ModRecipes(PackOutput generator, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(generator, completableFuture);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, new ItemStack(BlockRegister.ADV_TOFU_BARREL))
                .pattern("GGG")
                .pattern("TTT")
                .pattern(" B ")
                .define('G', TofuItems.ADVANCE_TOFUGEM.get())
                .define('T', TofuItems.TOFUMETAL.get())
                .define('B', Blocks.BARREL)
                .unlockedBy("has_item", has(TofuItems.ADVANCE_TOFUGEM.get()))
                .save(consumer);
    }
}
