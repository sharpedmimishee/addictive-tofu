package sharped.mimishee.addictivetofu.register;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, AddictiveTofu.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> ANKONIAN_AMBIENT = createEvent("mob.ankonian.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> ANKONIAN_HURT = createEvent("mob.ankonian.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ANKONIAN_DEATH = createEvent("mob.ankonian.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ANKONIAN_MESUGAKI = createEvent("mob.ankonian.mesugaki");

    private static DeferredHolder<SoundEvent, SoundEvent> createEvent(String sound) {
        ResourceLocation name = ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, sound);
        return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
    }


    private static Holder<SoundEvent> createHolderEvent(String sound) {
        ResourceLocation name = ResourceLocation.fromNamespaceAndPath(AddictiveTofu.MODID, sound);
        return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
    }
}
