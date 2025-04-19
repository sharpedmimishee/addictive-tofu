package sharped.mimishee.addictivetofu.items.tfenergy;

import baguchan.tofucraft.api.tfenergy.IEnergyContained;
import baguchan.tofucraft.api.tfenergy.IEnergyExtractable;
import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.api.tfenergy.TFEnergyData;
import baguchan.tofucraft.registry.TofuDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import sharped.mimishee.addictivetofu.AddictiveTofu;

import java.awt.*;
import java.util.List;

public class TFSwordItems extends SwordItem implements IEnergyInsertable, IEnergyContained, IEnergyExtractable {
    public static final ResourceLocation SWEEP_ID = ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, "sweep");

    public TFSwordItems(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, int attackDamage, float attackSpeed) {
        return createAttributes(tier, (float) attackDamage, attackSpeed);
    }

    public static ItemAttributeModifiers createAttributes(Tier p_330371_, float p_331976_, float p_332104_) {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double) (p_331976_ + p_330371_.getAttackDamageBonus()), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(SWEEP_ID, (double) p_332104_, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.SWEEPING_DAMAGE_RATIO, new AttributeModifier(SWEEP_ID, 0.1F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (getEnergy(stack) > 0) {
            if (!stack.has(DataComponents.TOOL)) {
                stack.set(DataComponents.TOOL, createToolProperties());
            }

            if (!stack.has(DataComponents.ATTRIBUTE_MODIFIERS)) {
                stack.set(DataComponents.ATTRIBUTE_MODIFIERS, createAttributes(this.getTier(), 4, -2.3F));
            }
        } else {
            if (stack.has(DataComponents.TOOL)) {
                stack.remove(DataComponents.TOOL);
            }

            if (stack.has(DataComponents.ATTRIBUTE_MODIFIERS)) {
                stack.remove(DataComponents.ATTRIBUTE_MODIFIERS);
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        drain(stack, 10, false);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        Tool tool = stack.get(DataComponents.TOOL);
        if (tool == null || getEnergy(stack) <= 0) {
            return false;
        } else {
            if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0F && tool.damagePerBlock() > 0) {
                drain(stack, 10, false);
            }

            return true;
        }
    }

    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        Tool tool = stack.get(DataComponents.TOOL);
        return tool != null && tool.isCorrectForDrops(state) && getEnergy(stack) > 0;
    }

    @Override
    public int fill(ItemStack inst, int energy, boolean simulate) {
        if (!simulate) {
            int calculated2 = Math.min(energy, this.getEnergyMax(inst) - this.getEnergy(inst));
            this.setEnergy(inst, this.getEnergy(inst) + calculated2);
            return calculated2;
        } else {
            return 0;
        }
    }

    @Override
    public int getEnergy(ItemStack inst) {
        return inst.get(TofuDataComponents.TF_ENERGY_DATA) != null ? ((TFEnergyData) inst.get(TofuDataComponents.TF_ENERGY_DATA)).storeTF() : 0;
    }

    @Override
    public int getEnergyMax(ItemStack inst) {
        return inst.get(TofuDataComponents.TF_ENERGY_DATA) != null ? ((TFEnergyData) inst.get(TofuDataComponents.TF_ENERGY_DATA)).maxTF() : 5000;
    }

    @Override
    public void setEnergy(ItemStack inst, int amount) {
        inst.set(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(amount, this.getEnergyMax(inst)));
    }

    @Override
    public void setEnergyMax(ItemStack inst, int amount) {
        inst.set(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(this.getEnergy(inst), amount));
    }

    private boolean getShowState(ItemStack stack) {
        return this.getEnergy(stack) != 0;
    }

    @Override
    public boolean isBarVisible(ItemStack p_150899_) {
        return this.getShowState(p_150899_) || super.isBarVisible(p_150899_);
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return this.getShowState(stack) ? Math.round((float) this.getEnergy(stack) / (float) this.getEnergyMax(stack) * 13.0F) : super.getBarWidth(stack);
    }

    @Override
    public int getBarColor(ItemStack p_150901_) {
        return this.getShowState(p_150901_) ? Color.white.getRGB() : super.getBarColor(p_150901_);
    }

    @Override
    public void appendHoverText(ItemStack p_43094_, Item.TooltipContext p_339613_, List<net.minecraft.network.chat.Component> p_43096_, TooltipFlag p_43097_) {
        super.appendHoverText(p_43094_, p_339613_, p_43096_, p_43097_);
        p_43096_.add(Component.translatable("tooltip.tofucraft.energy", new Object[]{this.getEnergy(p_43094_), this.getEnergyMax(p_43094_)}));
    }

    @Override
    public int drain(ItemStack inst, int amount, boolean simulate) {
        if (!simulate) {
            int calculated2 = Math.min(this.getEnergy(inst), amount);
            this.setEnergy(inst, this.getEnergy(inst) - calculated2);
            return calculated2;
        } else {
            return 0;
        }
    }
}
