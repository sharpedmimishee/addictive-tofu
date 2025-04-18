package sharped.mimishee.addictivetofu.providers;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.items.ItemRegister;

public class ItemModels extends ItemModelProvider {
    public ItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AddictiveTofu.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegister.NULL_TOFU.get());
    }
}
