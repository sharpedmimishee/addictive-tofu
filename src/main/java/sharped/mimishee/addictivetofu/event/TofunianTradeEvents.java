package sharped.mimishee.addictivetofu.event;

import baguchan.tofucraft.api.event.TofunianTradeEvent;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.items.ItemRegister;

import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = AddictiveTofu.MODID)
public class TofunianTradeEvents {
    @SubscribeEvent
    public static void tofunianTradeEvent(TofunianTradeEvent event) {
        List<VillagerTrades.ItemListing> itemListings = event.getTrades().get(5);
        itemListings.add(new SecondItemForZundaRubyTrade(ItemRegister.ZUNDA_CROSSBOW.get().getDefaultInstance(), TofuItems.ZUNDA_BOW.get().getDefaultInstance(), 20, 1, 2, 30, 0.2F));
    }

    static class ItemsForZundaRubyTrade implements VillagerTrades.ItemListing {
        private final ItemStack sellingItem;
        private final int rubyCount;
        private final int sellingItemCount;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;

        public ItemsForZundaRubyTrade(Block sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
            this(new ItemStack(sellingItem), rubyCount, sellingItemCount, maxUses, xpValue);
        }

        public ItemsForZundaRubyTrade(Item sellingItem, int rubyCount, int sellingItemCount, int xpValue) {
            this(new ItemStack(sellingItem), rubyCount, sellingItemCount, 12, xpValue);
        }

        public ItemsForZundaRubyTrade(Item sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
            this(new ItemStack(sellingItem), rubyCount, sellingItemCount, maxUses, xpValue);
        }

        public ItemsForZundaRubyTrade(ItemStack sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
            this(sellingItem, rubyCount, sellingItemCount, maxUses, xpValue, 0.05F);
        }

        public ItemsForZundaRubyTrade(ItemStack sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue, float priceMultiplier) {
            this.sellingItem = sellingItem;
            this.rubyCount = rubyCount;
            this.sellingItemCount = sellingItemCount;
            this.maxUses = maxUses;
            this.xpValue = xpValue;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity trader, RandomSource rand) {
            return new MerchantOffer(new ItemCost(TofuItems.ZUNDARUBY.get(), this.rubyCount), new ItemStack(this.sellingItem.getItem(), this.sellingItemCount), this.maxUses, this.xpValue, this.priceMultiplier);
        }
    }

    static class SecondItemForZundaRubyTrade implements VillagerTrades.ItemListing {
        private final ItemStack sellingItem;
        private final ItemStack needItem;
        private final int rubyCount;
        private final int sellingItemCount;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;

        public SecondItemForZundaRubyTrade(Block sellingItem, ItemStack needItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
            this(new ItemStack(sellingItem), needItem, rubyCount, sellingItemCount, maxUses, xpValue);
        }

        public SecondItemForZundaRubyTrade(Item sellingItem, ItemStack needItem, int rubyCount, int sellingItemCount, int xpValue) {
            this(new ItemStack(sellingItem), needItem, rubyCount, sellingItemCount, 12, xpValue);
        }

        public SecondItemForZundaRubyTrade(Item sellingItem, ItemStack needItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
            this(new ItemStack(sellingItem), needItem, rubyCount, sellingItemCount, maxUses, xpValue);
        }

        public SecondItemForZundaRubyTrade(ItemStack sellingItem, ItemStack needItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
            this(sellingItem, needItem, rubyCount, sellingItemCount, maxUses, xpValue, 0.05F);
        }

        public SecondItemForZundaRubyTrade(ItemStack sellingItem, ItemStack needItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue, float priceMultiplier) {
            this.sellingItem = sellingItem;
            this.needItem = needItem;
            this.rubyCount = rubyCount;
            this.sellingItemCount = sellingItemCount;
            this.maxUses = maxUses;
            this.xpValue = xpValue;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity trader, RandomSource rand) {
            return new MerchantOffer(new ItemCost(TofuItems.ZUNDARUBY.get(), this.rubyCount), Optional.of(new ItemCost(this.needItem.getItem(), 1)), new ItemStack(this.sellingItem.getItem(), this.sellingItemCount), this.maxUses, this.xpValue, this.priceMultiplier);
        }
    }
}
