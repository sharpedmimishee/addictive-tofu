package sharped.mimishee.addictivetofu.providers;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.block.BlockRegister;
import sharped.mimishee.addictivetofu.items.ItemRegister;

import java.util.function.Supplier;

public class ItemModelsGenerator extends ItemModelProvider {
    public ItemModelsGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AddictiveTofu.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegister.NULL_TOFU.get());
        basicItem(ItemRegister.ADV_TOFU.get());
        tfSwordItem(ItemRegister.TF_SWORD);
        simpleBlockItem(BlockRegister.ADV_TOFU_BARREL.get());
        simpleBlockItem(BlockRegister.ADV_TOFU_BLOCK.get());

        basicItem(ItemRegister.ACTIVE_NULL_TOFU.get());
        basicItem(ItemRegister.REDBEAN.get());
        glowCrossBowItem(ItemRegister.ZUNDA_CROSSBOW);
    }

    private ItemModelBuilder buildItem(String name, String parent, int emissivity, ResourceLocation... layers) {
        ItemModelBuilder builder = withExistingParent(name, parent);
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        if (emissivity > 0)
            builder = builder.customLoader(ItemLayerModelBuilder::begin).emissive(emissivity, emissivity, 0).renderType("minecraft:translucent", 0).end();
        return builder;
    }

    public ItemModelBuilder tfSwordItem(Supplier<? extends Item> item) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item.get());

        withExistingParent(id.getPath() + "_emissive", mcLoc("item/handheld"))
                .customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 1).renderType("minecraft:translucent", 1).end()
                .texture("layer0", modLoc("item/" + id.getPath()))
                .texture("layer1", modLoc("item/" + id.getPath() + "_emissive"));
        return withExistingParent(id.getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + id.getPath() + "_deactive"))
                .override().predicate(ResourceLocation.parse("charged"), 1).model(getExistingFile(modLoc("item/" + id.getPath() + "_emissive"))).end();
    }

    public ItemModelBuilder glowCrossBowItem(Supplier<? extends Item> item) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item.get());
        buildItem(id.getPath() + "_pulling_0", mcLoc("item/crossbow").toString(), 15, modLoc("item/" + id.getPath() + "_pulling_0"));
        buildItem(id.getPath() + "_pulling_1", mcLoc("item/crossbow").toString(), 15, modLoc("item/" + id.getPath() + "_pulling_1"));
        buildItem(id.getPath() + "_pulling_2", mcLoc("item/crossbow").toString(), 15, modLoc("item/" + id.getPath() + "_pulling_2"));
        buildItem(id.getPath() + "_arrow", mcLoc("item/crossbow").toString(), 15, modLoc("item/" + id.getPath() + "_arrow"));
        buildItem(id.getPath() + "_zunda_arrow", mcLoc("item/crossbow").toString(), 15, modLoc("item/" + id.getPath() + "_zunda_arrow"));
        buildItem(id.getPath() + "_firework", mcLoc("item/crossbow").toString(), 15, modLoc("item/" + id.getPath() + "_firework"));
        return withExistingParent(id.getPath(), mcLoc("item/crossbow"))
                .customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 0).renderType("minecraft:translucent", 0).end()
                .texture("layer0", modLoc("item/" + id.getPath()))
                .override().predicate(ResourceLocation.parse("pulling"), 1).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_0"))).end()
                .override().predicate(ResourceLocation.parse("pulling"), 1).predicate(ResourceLocation.parse("pull"), 0.58F).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_1"))).end()
                .override().predicate(ResourceLocation.parse("pulling"), 1).predicate(ResourceLocation.parse("pull"), 1).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_2"))).end()
                .override().predicate(ResourceLocation.parse("charged"), 1).model(getExistingFile(modLoc("item/" + id.getPath() + "_arrow"))).end()
                .override().predicate(ResourceLocation.parse("charged"), 1).predicate(ResourceLocation.parse("zunda_arrow"), 1).model(getExistingFile(modLoc("item/" + id.getPath() + "_zunda_arrow"))).end()
                .override().predicate(ResourceLocation.parse("charged"), 1).predicate(ResourceLocation.parse("firework"), 1).model(getExistingFile(modLoc("item/" + id.getPath() + "_firework"))).end();
    }

}
