package sharped.mimishee.addictivetofu.items;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;

public class ItemRegister {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AddictiveTofu.MODID);

    public static final DeferredItem<Item> NULL_TOFU = ITEMS.register("null_tofu",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(64)));
    public static final DeferredItem<Item> ACTIVE_NULL_TOFU = ITEMS.register("active_null_tofu",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(64)));
//    public static final DeferredItem<Item> ZUNDA_MIRROR = ITEMS.register("zunda_mirror",
//            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));
    public static final DeferredItem<Item> ZUNDA_CROSSBOW = ITEMS.register("zunda_crossbow",
            () -> new ZundaCrossbowItem(new Item.Properties().rarity(Rarity.RARE).durability(584).stacksTo(1)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
