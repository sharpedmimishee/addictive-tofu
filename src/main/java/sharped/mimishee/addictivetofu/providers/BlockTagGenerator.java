package sharped.mimishee.addictivetofu.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.block.BlockRegister;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AddictiveTofu.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(BlockRegister.ADV_TOFU_BARREL.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegister.ADV_TOFU_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(BlockRegister.TOFU_TERRAIN_REDBEAN.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(BlockRegister.ADV_TOFU_BLOCK.get());
    }
}
