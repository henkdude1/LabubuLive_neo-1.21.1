package net.henkdude.labubulive;

import com.mojang.logging.LogUtils;
import net.henkdude.labubulive.block.ModBlocks;
import net.henkdude.labubulive.item.ModCreativeModeTabs;
import net.henkdude.labubulive.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;        // <-- correct place for EventBusSubscriber
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import org.slf4j.Logger;

/**
 * Main mod class for Labubu Live.
 * NeoForge automatically injects the mod's event bus and ModContainer into the constructor.
 */
@Mod(LabubuLive.MOD_ID)
public class LabubuLive {

    public static final String MOD_ID = "labubulive";
    public static final Logger LOGGER = LogUtils.getLogger();

    /**
     * NeoForge 21.1.x style constructor: receives the mod event bus and the mod container.
     */
    public LabubuLive(IEventBus modEventBus, ModContainer container) {

        // Register setup events on the mod event bus
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        // Register your blocks/items/tabs on the mod event bus
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModDataComponents.register(modEventBus);

        // Register your config with the container if you have one:
        // container.registerConfig(net.neoforged.neoforge.common.ModConfigSpec.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Do common setup here
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.LABUBU_LUCK);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.LABUBU_BLINDBOX);
        }
    }

    // Example of a server starting event subscription
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when server starts
    }

    // Client-only setup events registered via the mod event bus
    @EventBusSubscriber(modid = LabubuLive.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Do client-only setup here
        }
    }
}
