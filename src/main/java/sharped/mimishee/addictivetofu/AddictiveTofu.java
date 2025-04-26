package sharped.mimishee.addictivetofu;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import sharped.mimishee.addictivetofu.block.BlockRegister;
import sharped.mimishee.addictivetofu.blockentity.BlockEntityRegister;
import sharped.mimishee.addictivetofu.entity.EntityRegister;
import sharped.mimishee.addictivetofu.items.ItemRegister;
import sharped.mimishee.addictivetofu.recipe.RecipeRegister;
import sharped.mimishee.addictivetofu.register.CreativeModeTabRegister;
import sharped.mimishee.addictivetofu.register.ModSounds;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AddictiveTofu.MODID)
public class AddictiveTofu
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "addictivetofu";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public AddictiveTofu(IEventBus modEventBus, ModContainer modContainer)
    {

        modEventBus.addListener(this::commonSetup);
        BlockRegister.BLOCKS.register(modEventBus);
        ItemRegister.register(modEventBus);
        CreativeModeTabRegister.CREATIVE_MODE_TABS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        EntityRegister.ENTITIES.register(modEventBus);
        BlockEntityRegister.register(modEventBus);
        RecipeRegister.register(modEventBus);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (AddictiveTofu) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
//        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

//        if (Config.logDirtBlock)
//            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

//        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

//        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
