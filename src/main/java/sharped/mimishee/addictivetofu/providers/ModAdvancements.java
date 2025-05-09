package sharped.mimishee.addictivetofu.providers;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import sharped.mimishee.addictivetofu.block.BlockRegister;
import sharped.mimishee.addictivetofu.items.ItemRegister;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancements extends AdvancementProvider {
    public ModAdvancements(PackOutput output,
                           CompletableFuture<HolderLookup.Provider> lookupProvider,
                           ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper, List.of(new maingen()));
    }

    private static final class maingen implements AdvancementProvider.AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            new ItemStack(ItemRegister.NULL_TOFU.get()),
                            Component.translatable("advancements.addictivetofu.main.root.title"),
                            Component.translatable("advancements.addictivetofu.main.root.description"),
                            // The background texture. Use null if you don't want a background texture (for non-root advancements).
                            ResourceLocation.fromNamespaceAndPath("addictivetofu","textures/gui/advancements/background.png"),
                            AdvancementType.TASK,// The frame type. Valid values are AdvancementType.TASK, CHALLENGE, or GOAL.
                            true, //Toast
                            true, //chat
                            false //hidden or not
                    )
                    .addCriterion("temp", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.TOFUMOMEN.get()))
                    .requirements(AdvancementRequirements.anyOf(List.of("temp")))
                    .rewards(AdvancementRewards.Builder.experience(100))
                    .save(consumer,
                            ResourceLocation.fromNamespaceAndPath("addictivetofu", "main/root"),
                            existingFileHelper);
            AdvancementHolder gah = Advancement.Builder.advancement()
                    .display(
                            new ItemStack(ItemRegister.NULL_TOFU.get()),
                            Component.translatable("advancements.addictivetofu.main.gah.title"),
                            Component.translatable("advancements.addictivetofu.main.gah.description"),
                            // The background texture. Use null if you don't want a background texture (for non-root advancements
                            null,
                            AdvancementType.TASK,// The frame type. Valid values are AdvancementType.TASK, CHALLENGE, or GOAL.
                            true, //Toast
                            true, //chat
                            true //hidden or not
                    )
                    .parent(root)
                    .addCriterion("has_null_tofu", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegister.NULL_TOFU))
                    .requirements(AdvancementRequirements.anyOf(List.of("has_null_tofu")))
                    .rewards(AdvancementRewards.Builder.experience(100))
                    .save(consumer,
                            ResourceLocation.fromNamespaceAndPath("addictivetofu", "main/gah"),
                            existingFileHelper);
            AdvancementHolder Activated = Advancement.Builder.advancement()
                    .display(
                            new ItemStack(ItemRegister.ACTIVE_NULL_TOFU.get()),
                            Component.translatable("advancements.addictivetofu.main.activated.title"),
                            Component.translatable("advancements.addictivetofu.main.activated.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .parent(gah)
                    .addCriterion("has_active_null_tofu", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegister.ACTIVE_NULL_TOFU))
                    .requirements(AdvancementRequirements.anyOf(List.of("has_active_null_tofu")))
                    .rewards(AdvancementRewards.Builder.experience(1000))
                    .save(consumer,
                            ResourceLocation.fromNamespaceAndPath("addictivetofu", "main/activated"),
                            existingFileHelper);

            AdvancementHolder advance_tofu = Advancement.Builder.advancement()
                    .display(
                            new ItemStack(ItemRegister.ADV_TOFU.get()),
                            Component.translatable("advancements.addictivetofu.main.adv_tofu.title"),
                            Component.translatable("advancements.addictivetofu.main.adv_tofu.description"),
                            // The background texture. Use null if you don't want a background texture (for non-root advancements
                            null,
                            AdvancementType.TASK,// The frame type. Valid values are AdvancementType.TASK, CHALLENGE, or GOAL.
                            true, //Toast
                            true, //chat
                            false //hidden or not
                    )
                    .parent(root)
                    .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegister.ADV_TOFU))
                    .save(consumer,
                            ResourceLocation.fromNamespaceAndPath("addictivetofu", "main/adv_tofu"),
                            existingFileHelper);
            AdvancementHolder compounding_cauldron = Advancement.Builder.advancement()
                    .display(
                            new ItemStack(BlockRegister.COMPOUNDING_CAULDRON.asItem()),
                            Component.translatable("advancements.addictivetofu.main.compounding_cauldron.title"),
                            Component.translatable("advancements.addictivetofu.main.compounding_cauldron.desc"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .parent(root)
                    .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegister.COMPOUNDING_CAULDRON))
                    .save(consumer,
                            ResourceLocation.fromNamespaceAndPath("addictivetofu", "main/compounding_cauldron"),
                            existingFileHelper);


            AdvancementHolder tofu_paper = Advancement.Builder.advancement()
                    .display(
                            new ItemStack(ItemRegister.TOFU_PAPER.get()),
                            Component.translatable("advancements.addictivetofu.main.tofu_paper.title"),
                            Component.translatable("advancements.addictivetofu.main.tofu_paper.description"),
                            // The background texture. Use null if you don't want a background texture (for non-root advancements
                            null,
                            AdvancementType.TASK,// The frame type. Valid values are AdvancementType.TASK, CHALLENGE, or GOAL.
                            true, //Toast
                            true, //chat
                            false //hidden or not
                    )
                    .parent(compounding_cauldron)
                    .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegister.TOFU_PAPER))
                    .save(consumer,
                            ResourceLocation.fromNamespaceAndPath("addictivetofu", "main/tofu_paper"),
                            existingFileHelper);
        }
    }
}
