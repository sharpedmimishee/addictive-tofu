package sharped.mimishee.addictivetofu.items;


import baguchan.tofucraft.registry.TofuItemTier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.block.BlockRegister;
import sharped.mimishee.addictivetofu.entity.EntityRegister;
import sharped.mimishee.addictivetofu.items.tfenergy.TFSwordItems;

public class ItemRegister {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AddictiveTofu.MODID);

    public static final DeferredItem<Item> NULL_TOFU = ITEMS.register("null_tofu",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(64)));
    public static final DeferredItem<Item> ADV_TOFU = ITEMS.register("adv_tofu",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TF_SWORD = ITEMS.register("tf_sword",
            () -> new TFSwordItems(TofuItemTier.TOFUDIAMOND, new Item.Properties().attributes(TFSwordItems.createAttributes(TofuItemTier.TOFUDIAMOND, 4, -2.3F)).stacksTo(1)));
    public static final DeferredItem<Item> ACTIVE_NULL_TOFU = ITEMS.register("active_null_tofu",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(64)));
    public static final DeferredItem<Item> ZUNDA_MIRROR = ITEMS.register("zunda_mirror",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));
    public static final DeferredItem<Item> ZUNDA_INGOT = ITEMS.register("zunda_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REDBEAN = ITEMS.register("redbean",
            () -> new ItemNameBlockItem(BlockRegister.REDBEAN_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> MAGIC_BEAN = ITEMS.register("magicbean",
            () -> new ItemNameBlockItem(BlockRegister.MAGIC_BEAN.get(), new Item.Properties()));
    public static final DeferredItem<Item> REDBEAN_PASTE = ITEMS.register("redbean_paste",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).build())));
    public static final DeferredItem<Item> ZUNDA_CROSSBOW = ITEMS.register("zunda_crossbow",
            () -> new ZundaCrossbowItem(new Item.Properties().rarity(Rarity.RARE).durability(584).stacksTo(1)));
    public static final DeferredItem<Item> ANKO_BOW = ITEMS.register("anko_bow",
            () -> new AnkoBowItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(484).stacksTo(1)));
    public static final DeferredItem<Item> ANKONIAN_SPAWN_EGG = ITEMS.register("ankonian_spawn_egg",
            () -> new SpawnEggItem(EntityRegister.ANKONIAN.get(), 0xF6E9EF, 0xE4C7D5, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> CRIMSON_HUNTER_SPAWN_EGG = ITEMS.register("crimson_hunter_spawn_egg",
            () -> new SpawnEggItem(EntityRegister.CRIMSON_HUNTER.get(), 0xF6E9EF, 0x5E1C47, new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
