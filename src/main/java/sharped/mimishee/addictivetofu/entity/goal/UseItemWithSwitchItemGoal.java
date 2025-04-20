package sharped.mimishee.addictivetofu.entity.goal;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.UseItemGoal;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.Predicate;

public class UseItemWithSwitchItemGoal<T extends Mob> extends UseItemGoal<T> {
    private final T mob;
    public int cooldown = 0;

    public UseItemWithSwitchItemGoal(T mob, ItemStack item, @Nullable SoundEvent finishUsingSound, Predicate<? super T> canUseSelector) {
        super(mob, item, finishUsingSound, canUseSelector);
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.cooldown <= 0 && this.mob.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {
            if (super.canUse()) {
                this.cooldown = 600 + this.mob.getRandom().nextInt(3) * 200;
                return true;
            }
        } else {
            if (this.cooldown > 0) {
                this.cooldown--;
            }
            return false;
        }
        return false;
    }

    @Override
    public void start() {
        this.mob.stopUsingItem();
        this.mob.setItemSlot(EquipmentSlot.OFFHAND, this.mob.getItemBySlot(EquipmentSlot.MAINHAND).copy());
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        this.mob.setItemSlot(EquipmentSlot.MAINHAND, this.mob.getItemBySlot(EquipmentSlot.OFFHAND).copy());
        this.mob.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
        this.cooldown = 600 + this.mob.getRandom().nextInt(3) * 200;
    }
}
