package sharped.mimishee.addictivetofu.support;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.block.BlockRegister;
import sharped.mimishee.addictivetofu.recipe.CompoundingCauldronRecipe;
import sharped.mimishee.addictivetofu.recipe.RecipeRegister;
import sharped.mimishee.addictivetofu.support.jei.CompoundingCauldronCategory;

import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, "addictivetofu/jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CompoundingCauldronCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<CompoundingCauldronRecipe> CompoundingCauldronRecipes = recipeManager
                .getAllRecipesFor(RecipeRegister.COMPOUNDING_CAULDRON_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(CompoundingCauldronCategory.COMPOUNDING_CAULDRON_RECIPETYPE, CompoundingCauldronRecipes);
    }


    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(BlockRegister.COMPOUNDING_CAULDRON.asItem()),
                CompoundingCauldronCategory.COMPOUNDING_CAULDRON_RECIPETYPE);
    }
}
