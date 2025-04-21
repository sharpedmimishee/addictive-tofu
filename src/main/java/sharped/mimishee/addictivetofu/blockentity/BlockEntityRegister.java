package sharped.mimishee.addictivetofu.blockentity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.block.BlockRegister;

import java.util.function.Supplier;

public class BlockEntityRegister {
    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AddictiveTofu.MODID);

    public static final Supplier<BlockEntityType<CompoundingCauldronEntity>> COMPOUNDING_CAULDRON_ENTITY = BLOCKENTITIES.register("compounding_cauldron_entity",
            () -> BlockEntityType.Builder.of(
                            CompoundingCauldronEntity::new,
                            BlockRegister.COMPOUNDING_CAULDRON.get(), BlockRegister.COMPOUNDING_CAULDRON.get()
                    )
                    .build(null));

    public static void register(IEventBus bus) {
        BLOCKENTITIES.register(bus);
    }
}
