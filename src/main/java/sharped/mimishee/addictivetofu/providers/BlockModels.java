package sharped.mimishee.addictivetofu.providers;

import baguchan.tofucraft.block.utils.WeightBaseBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.block.BlockRegister;

public class BlockModels extends BlockStateProvider {


    public BlockModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AddictiveTofu.MODID, existingFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {
        this.simpleBlock(BlockRegister.ADV_TOFU_BLOCK.get());
        this.barrelBlock(BlockRegister.ADV_TOFU_BARREL.get());
    }

    public void barrelBlock(Block block) {
        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(WeightBaseBlock.TIME);
            ModelFile barrel_1 = models().cube(name(block), ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, "block/barrel_adv_tofu_top"), ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, "block/barrel_adv_tofu_top"), texture(name(block) + "_side"), texture(name(block) + "_side"), texture(name(block) + "_side"), texture(name(block) + "_side")).texture("particle", texture(name(block) + "_side"));
            ModelFile barrel_2 = models().cube(name(block) + "_fin", ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, "block/barrel_adv_tofu_top_fin"), ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, "block/barrel_adv_tofu_top_fin"), texture(name(block) + "_side_fin"), texture(name(block) + "_side_fin"), texture(name(block) + "_side_fin"), texture(name(block) + "_side_fin")).texture("particle", texture(name(block) + "_side_fin"));
            return ConfiguredModel.builder()
                    .modelFile(age == 5 ? barrel_2 : barrel_1)
                    .build();
        });
    }

    protected ResourceLocation texture(String name) {
        return modLoc("block/" + name);
    }

    protected String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

}
