package sharped.mimishee.addictivetofu.items;

import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.entity.projectile.ZundaArrow;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class ZundaCrossbowItem extends CrossbowItem implements IEnergyInsertable {
    private static final ChargingSounds DEFAULT_SOUNDS = new ChargingSounds(Optional.of(SoundEvents.CROSSBOW_LOADING_START), Optional.of(SoundEvents.CROSSBOW_LOADING_MIDDLE), Optional.of(SoundEvents.CROSSBOW_LOADING_END));

    public ZundaCrossbowItem(Item.Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
        return !isTooDamagedToUse(p_40673_.getItemInHand(p_40674_)) ? super.use(p_40672_, p_40673_, p_40674_) : InteractionResultHolder.fail(p_40673_.getItemInHand(p_40674_));
    }

    private static boolean isTooDamagedToUse(ItemStack stack) {
        return stack.getDamageValue() >= stack.getMaxDamage() - 1;
    }

    @Override
    protected Projectile createProjectile(Level level, LivingEntity livingEntity, ItemStack stack, ItemStack ammo, boolean isCrit) {
        Projectile projectile = super.createProjectile(level, livingEntity, stack, ammo, isCrit);
        if (projectile instanceof ZundaArrow zundaArrow) {
            zundaArrow.setBaseDamage(zundaArrow.getBaseDamage() + (double) 3.0F);
        }

        if (projectile instanceof AbstractArrow zundaArrow) {
            zundaArrow.setBaseDamage(zundaArrow.getBaseDamage() + (double) 0.5F);
        }

        return projectile;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (!isTooDamagedToUse(stack)) {

            int i = this.getUseDuration(stack, entityLiving) - timeLeft;
            float f = getPowerForTime(i, stack, entityLiving);
            if (f >= 1.0F && !isCharged(stack) && tryLoadProjectiles(entityLiving, stack)) {
                CrossbowItem.ChargingSounds crossbowitem$chargingsounds = this.getChargingSounds(stack);
                crossbowitem$chargingsounds.end()
                        .ifPresent(
                                p_352852_ -> level.playSound(
                                        null,
                                        entityLiving.getX(),
                                        entityLiving.getY(),
                                        entityLiving.getZ(),
                                        p_352852_.value(),
                                        entityLiving.getSoundSource(),
                                        1.0F,
                                        1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F
                                )
                        );
            }
        }
    }

    ChargingSounds getChargingSounds(ItemStack stack) {
        return (ChargingSounds) EnchantmentHelper.pickHighestLevel(stack, EnchantmentEffectComponents.CROSSBOW_CHARGING_SOUNDS).orElse(DEFAULT_SOUNDS);
    }

    private static boolean tryLoadProjectiles(LivingEntity shooter, ItemStack crossbowStack) {
        List<ItemStack> list = draw(crossbowStack, shooter.getProjectile(crossbowStack), shooter);
        if (!list.isEmpty()) {
            crossbowStack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(list));
            return true;
        } else {
            return false;
        }
    }


    @Override
    public int getEnchantmentValue() {
        return 5;
    }


    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return super.supportsEnchantment(stack, enchantment) || enchantment.is(EnchantmentTags.CROSSBOW_EXCLUSIVE);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return getChargeDuration(stack, entity) + 3;
    }

    public static int getChargeDuration(ItemStack stack, LivingEntity shooter) {
        float f = EnchantmentHelper.modifyCrossbowChargingTime(stack, shooter, 1.0F);
        return Mth.floor(f * 20.0F);
    }

    private static float getPowerForTime(int timeLeft, ItemStack stack, LivingEntity shooter) {
        float f = (float) timeLeft / (float) getChargeDuration(stack, shooter);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int fill(ItemStack inst, int energy, boolean simulate) {
        int calculated = Math.min(energy, inst.getDamageValue());
        if (!simulate && inst.getDamageValue() > 0) {
            inst.setDamageValue(Mth.clamp(inst.getDamageValue() - calculated, 0, inst.getMaxDamage()));
            return calculated * 5;
        } else {
            return 0;
        }
    }
}
