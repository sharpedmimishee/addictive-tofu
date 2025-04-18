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
                            Component.translatable("advancements.addictivetofu.main.null_tofu.title"),
                            Component.translatable("advancements.addictivetofu.main.null_tofu.description"),
                            // The background texture. Use null if you don't want a background texture (for non-root advancements
                            null,
                            AdvancementType.TASK,// The frame type. Valid values are AdvancementType.TASK, CHALLENGE, or GOAL.
                            true, //Toast
                            true, //chat
                            false //hidden or not
                    )
                    .parent(root)
                    .addCriterion("has_null_tofu", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegister.NULL_TOFU))
                    .requirements(AdvancementRequirements.anyOf(List.of("has_null_tofu")))
                    .rewards(AdvancementRewards.Builder.experience(100))
                    .save(consumer,
                            ResourceLocation.fromNamespaceAndPath("addictivetofu", "main/null_tofu"),
                            existingFileHelper);
        }
    }
}
