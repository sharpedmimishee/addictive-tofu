package sharped.mimishee.addictivetofu.register;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.world.gen.structure.mansion.TofuMansionStructure;

public class ModStructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE = DeferredRegister.create(BuiltInRegistries.STRUCTURE_TYPE, AddictiveTofu.MODID);

    public static final DeferredHolder<StructureType<?>, StructureType<TofuMansionStructure>> TOFU_MANSION = STRUCTURE_TYPE.register("tofu_mansions", (r) -> () -> TofuMansionStructure.CODEC);

}
