package sharped.mimishee.addictivetofu.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.blocks.BlockRegister;

import java.util.function.Function;

public class BlockStateGenerator extends BlockStateProvider {
    public BlockStateGenerator(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeCrop((CropBlock) BlockRegister.REDBEAN_CROP.get(), "azuki_crop", "azuki_crop");
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
            models[0] =
                    new ConfiguredModel(models().crop(modelName + block.AGE,
                            ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID,
                                    "block/" + textureName + block.AGE)).renderType("cutout"));
        return models;
    }
}
