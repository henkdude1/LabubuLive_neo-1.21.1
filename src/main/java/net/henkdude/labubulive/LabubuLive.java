package net.henkdude.labubulive;

import com.mojang.logging.LogUtils;
import net.henkdude.labubulive.block.ModBlocks;
import net.henkdude.labubulive.item.ModCreativeModeTabs;
import net.henkdude.labubulive.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
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

@Mod(LabubuLive.MOD_ID)
public class LabubuLive {
    public static final String MOD_ID = "labubulive";
    private static final Logger LOGGER = LogUtils.getLogger();

    // NeoForge will inject these constructor params
    public LabubuLive(IEventBus modEventBus, ModContainer modContainer) {
        // Common setup
        modEventBus.addListener(this::commonSetup);

        // Register this class on the GAME bus for non-modbus events
        NeoForge.EVENT_BUS.register(this);

        // Registries on the MOD bus
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModDataComponents.register(modEventBus);

        // Creative tab population
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // do common init here
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.LABUBU_LUCK);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.LABUBU_BLINDBOX);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // server starting logic here (GAME bus)
    }

    // Client-only subscribers (auto-bus routing; no 'bus =' parameter!)
    @EventBusSubscriber(modid = LabubuLive.MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // client setup here (MOD bus event)
        }
    }
}
