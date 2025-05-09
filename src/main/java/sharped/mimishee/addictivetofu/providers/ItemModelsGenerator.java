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
        basicItem(ItemRegister.TOFU_JADE.get());
        basicItem(ItemRegister.ZUNDA_STEEL.get());
        basicItem(ItemRegister.POLISHING_TOFU_JADE.get());
        basicItem(ItemRegister.LUSTROUS_TOFU_JADE.get());
        basicItem(ItemRegister.PALE_GLOWING_TOFU_JADE.get());
        basicItem(ItemRegister.GLOWING_TOFU_JADE.get());
        tfSwordItem(ItemRegister.TF_SWORD);
        simpleBlockItem(BlockRegister.ADV_TOFU_BARREL.get());
        simpleBlockItem(BlockRegister.ADV_TOFU_BLOCK.get());
        simpleBlockItem(BlockRegister.TOFU_TERRAIN_REDBEAN.get());

        basicItem(ItemRegister.ACTIVE_NULL_TOFU.get());
        basicItem(ItemRegister.REDBEAN.get());
        basicItem(ItemRegister.TOFU_PAPER.get());
        glowCrossBowItem(ItemRegister.ZUNDA_CROSSBOW);
        bowItem(ItemRegister.ANKO_BOW);
        spawnEggItem(ItemRegister.ANKONIAN_SPAWN_EGG.asItem());
        spawnEggItem(ItemRegister.CRIMSON_HUNTER_SPAWN_EGG.asItem());
        spawnEggItem(ItemRegister.ZUNDA_SLIME_SPAWN_EGG.asItem());
        spawnEggItem(ItemRegister.REDBEAN_SLIME_SPAWN_EGG.asItem());
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

    public ItemModelBuilder bowItem(Supplier<? extends Item> item) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item.get());
        withExistingParent(id.getPath() + "_pulling_0", mcLoc("item/bow")).texture("layer0", modLoc("item/" + id.getPath() + "_pulling_0"));
        withExistingParent(id.getPath() + "_pulling_1", mcLoc("item/bow")).texture("layer0", modLoc("item/" + id.getPath() + "_pulling_1"));
        withExistingParent(id.getPath() + "_pulling_2", mcLoc("item/bow")).texture("layer0", modLoc("item/" + id.getPath() + "_pulling_2"));
        return withExistingParent(id.getPath(), mcLoc("item/bow"))
                .texture("layer0", modLoc("item/" + id.getPath()))
                .override().predicate(ResourceLocation.parse("pulling"), 1).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_0"))).end()
                .override().predicate(ResourceLocation.parse("pulling"), 1).predicate(ResourceLocation.parse("pull"), 0.65F).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_1"))).end()
                .override().predicate(ResourceLocation.parse("pulling"), 1).predicate(ResourceLocation.parse("pull"), 0.9F).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_2"))).end();
    }

}
