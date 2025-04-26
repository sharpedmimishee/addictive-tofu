package sharped.mimishee.addictivetofu.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;

import java.util.function.Supplier;

public class RecipeRegister {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, AddictiveTofu.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, AddictiveTofu.MODID);

    public static final Supplier<RecipeSerializer<CompoundingCauldronRecipe>> COMPOUNDING_CAULDRON_SERIALIZER =
            SERIALIZERS.register("compounding_cauldron", CompoundingCauldronRecipe.CompoundingCauldronSerializer::new);
    public static final Supplier<RecipeType<CompoundingCauldronRecipe>> COMPOUNDING_CAULDRON_TYPE =
            TYPES.register("compounding_cauldron", () -> new RecipeType<CompoundingCauldronRecipe>() {
                @Override
                public String toString() {
                    return "compounding_cauldron";
                }
            });


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
