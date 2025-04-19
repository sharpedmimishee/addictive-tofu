package sharped.mimishee.addictivetofu.items;


import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.blocks.BlockRegister;

public class ItemRegister {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AddictiveTofu.MODID);

    public static final DeferredItem<Item> NULL_TOFU = ITEMS.register("null_tofu",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(64)));
    public static final DeferredItem<Item> ACTIVE_NULL_TOFU = ITEMS.register("active_null_tofu",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(64)));
//    public static final DeferredItem<Item> ZUNDA_MIRROR = ITEMS.register("zunda_mirror",
//            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));
    public static final DeferredItem<Item> ZUNDA_INGOT = ITEMS.register("zunda_ingot",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REDBEAN = ITEMS.register("redbean",
            () -> new ItemNameBlockItem(BlockRegister.REDBEAN_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> REDBEAN_PASTE = ITEMS.register("redbean_paste",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).build())));
    public static final DeferredItem<Item> ZUNDA_CROSSBOW = ITEMS.register("zunda_crossbow",
            () -> new ZundaCrossbowItem(new Item.Properties().rarity(Rarity.RARE).durability(584).stacksTo(1)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
