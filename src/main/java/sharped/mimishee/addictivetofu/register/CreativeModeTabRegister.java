package sharped.mimishee.addictivetofu.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.block.BlockRegister;
import sharped.mimishee.addictivetofu.items.ItemRegister;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreativeModeTabRegister {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AddictiveTofu.MODID);
    public static final Supplier<CreativeModeTab> ADDTICTIVE_TOFU = CREATIVE_MODE_TABS.register(AddictiveTofu.MODID, () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.SPAWN_EGGS).title(Component.translatable("itemGroup." + AddictiveTofu.MODID)).icon(() -> ItemRegister.ZUNDA_CROSSBOW.get().asItem().getDefaultInstance()).displayItems((parameters, output) -> {
        output.acceptAll(Stream.of(ItemRegister.REDBEAN, ItemRegister.REDBEAN_PASTE, ItemRegister.MAGIC_BEAN, ItemRegister.ANKO_BOW
                , ItemRegister.ANKONIAN_SPAWN_EGG, ItemRegister.CRIMSON_HUNTER_SPAWN_EGG, ItemRegister.REDBEAN_SLIME_SPAWN_EGG, ItemRegister.ZUNDA_SLIME_SPAWN_EGG
                , ItemRegister.ZUNDA_CROSSBOW, ItemRegister.TF_SWORD, ItemRegister.ADV_TOFU, ItemRegister.NULL_TOFU, ItemRegister.ACTIVE_NULL_TOFU).map((sup) -> sup.get().asItem().getDefaultInstance()).toList());
        output.acceptAll(Stream.of(BlockRegister.ADV_TOFU_BARREL, BlockRegister.ADV_TOFU_BLOCK, BlockRegister.COMPOUNDING_CAULDRON, BlockRegister.TOFU_TERRAIN_REDBEAN).map((sup) -> sup.get().asItem().getDefaultInstance()).toList());
    }).build());
}
