package sharped.mimishee.addictivetofu.support.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.block.BlockRegister;
import sharped.mimishee.addictivetofu.recipe.CompoundingCauldronRecipe;
import sharped.mimishee.addictivetofu.recipe.RecipeRegister;

public class CompoundingCauldronCategory implements IRecipeCategory<CompoundingCauldronRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, "compounding_cauldron");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID,
            "textures/gui/blocks/compounding_cauldron_gui.png");

    public static final RecipeType<CompoundingCauldronRecipe> COMPOUNDING_CAULDRON_RECIPETYPE =
            new RecipeType<>(UID, CompoundingCauldronRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CompoundingCauldronCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(TEXTURE,0 ,0, 176, 73).setTextureSize(176, 73).build();
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegister.COMPOUNDING_CAULDRON));
    }

    @Override
    public RecipeType<CompoundingCauldronRecipe> getRecipeType() {
        return COMPOUNDING_CAULDRON_RECIPETYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.addictivetofu.compounding_cauldron");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CompoundingCauldronRecipe recipe, IFocusGroup focuses) {
        for (var i=0; i < recipe.itemsInput().size();i++) {
            int x;
            if (i == 0 || i == 3 || i == 6) { x=12; } else if (i == 2 || i == 4 || i == 7) { x=33; } else { x=54; }
            int y;
            if (i < 3) { y = 14; } else if (i < 6) { y=35; } else {y=56;}
            builder.addSlot(RecipeIngredientRole.INPUT, x-7, y-7).addIngredients(recipe.getIngredients().get(i));
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 68, 28).addFluidStack(recipe.getFluidInput().getFluid(), 1000);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 129, 28).addItemStack(recipe.getResultItem(null));
    }
}
