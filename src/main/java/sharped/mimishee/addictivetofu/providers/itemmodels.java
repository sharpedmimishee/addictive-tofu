package sharped.mimishee.addictivetofu.providers;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.items.itemsregister;

public class itemmodels extends ItemModelProvider {
    public itemmodels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AddictiveTofu.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(itemsregister.NULL_TOFU.get());
    }
}
