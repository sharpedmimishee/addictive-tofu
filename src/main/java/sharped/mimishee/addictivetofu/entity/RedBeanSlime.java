package sharped.mimishee.addictivetofu.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;

public class RedBeanSlime extends Slime {
    public RedBeanSlime(EntityType<? extends Slime> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 5;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double) 5.0F)
                .add(Attributes.FOLLOW_RANGE, (double) 25.0F)
                .add(Attributes.MAX_HEALTH, (double) 11.0F)
                .add(Attributes.SCALE, (double) 2.0F)
                .add(Attributes.GRAVITY, (double) 50.0F)
                .add(Attributes.ARMOR, (double) 12.0F)
                .add(Attributes.ATTACK_DAMAGE, (double) 12.0F)
                .add(Attributes.JUMP_STRENGTH, (double) 5.0F)
                .add(Attributes.ARMOR, (double) 15.0F)
                .add(Attributes.SAFE_FALL_DISTANCE, (double) 20.0F);
    }
}
