package sharped.mimishee.addictivetofu.entity;

import baguchan.tofucraft.entity.AbstractTofunian;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import sharped.mimishee.addictivetofu.register.ModTags;

public class AbstractAnkonian extends Monster {
    public AbstractAnkonian(EntityType<? extends AbstractAnkonian> entityType, Level level) {
        super(entityType, level);
    }


    public boolean canAttack(LivingEntity target) {
        return target instanceof AbstractTofunian && target.isBaby() ? false : super.canAttack(target);
    }

    public boolean isAlliedTo(Entity entity) {
        if (super.isAlliedTo(entity)) {
            return true;
        } else {
            return !entity.getType().is(ModTags.EntityType.ANKONIAN_FRIENDS) ? false : this.getTeam() == null && entity.getTeam() == null;
        }
    }
}
