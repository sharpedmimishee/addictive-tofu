package sharped.mimishee.addictivetofu.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;

import java.util.function.Supplier;

@EventBusSubscriber(modid = AddictiveTofu.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, AddictiveTofu.MODID);

    public static final Supplier<EntityType<Ankonian>> ANKONIAN = ENTITIES.register("ankonian", () -> EntityType.Builder.of(Ankonian::new, MobCategory.MONSTER)
            .sized(0.6F, 1.2F).eyeHeight(1.2F * 0.8F).clientTrackingRange(10).passengerAttachments(1.2F).ridingOffset(-0.3F).build(AddictiveTofu.MODID + ":ankonian"));
    public static final Supplier<EntityType<CrimsonHunter>> CRIMSON_HUNTER = ENTITIES.register("crimson_hunter", () -> EntityType.Builder.of(CrimsonHunter::new, MobCategory.MONSTER)
            .sized(0.6F, 1.6F).eyeHeight(1.6F * 0.85F).clientTrackingRange(10).passengerAttachments(1.6F).ridingOffset(-0.3F).build(AddictiveTofu.MODID + ":crimson_hunter"));


    @SubscribeEvent
    public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
        event.put(ANKONIAN.get(), Ankonian.createAttributes().build());
        event.put(CRIMSON_HUNTER.get(), CrimsonHunter.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerEntityAttribute(RegisterSpawnPlacementsEvent event) {
        event.register(ANKONIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Ankonian::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(CRIMSON_HUNTER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrimsonHunter::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
    }
}
