package sharped.mimishee.addictivetofu.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;

public class ZundaSlime extends Slime {
    public ZundaSlime(EntityType<? extends Slime> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 3;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double) 1.2F)
                .add(Attributes.FOLLOW_RANGE, (double) 25.0F)
                .add(Attributes.MAX_HEALTH, (double) 1024.0F)
                .add(Attributes.SCALE, (double) 6.0F)
                .add(Attributes.ARMOR, (double) 12.0F)
                .add(Attributes.ATTACK_DAMAGE, (double) 5.0F)
                .add(Attributes.JUMP_STRENGTH, (double) 0.8F)
                .add(Attributes.ARMOR, (double) 40.0F)
                .add(Attributes.SAFE_FALL_DISTANCE, (double) 20.0F);
    }
}
