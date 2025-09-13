package net.henkdude.labubulive.item;

import net.henkdude.labubulive.LabubuLive;
import net.henkdude.labubulive.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab>CREATIVE_MODE_TABS=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LabubuLive.MOD_ID);

    public static final RegistryObject<CreativeModeTab>LABUBU=CREATIVE_MODE_TABS.register("labubu_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.LABUBU_LUCK.get()))
                    .title(Component.translatable("creativetab.labubulive.labubu_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.LABUBU_BLINDBOX.get());
                        pOutput.accept(ModItems.LABUBU_LUCK.get());
                        pOutput.accept(ModItems.LABUBU_CHECKMATE.get());
                        pOutput.accept(ModItems.LABUBU_FLIP_WITH_ME.get());
                        pOutput.accept(ModItems.LABUBU_PUMPKIN.get());
                        pOutput.accept(ModItems.LABUBU_FALL.get());
                        pOutput.accept(ModItems.LABUBU_SKATER.get());
                        pOutput.accept(ModItems.LABUBU_ANGEL.get());
                        pOutput.accept(ModItems.LABUBU_MINER.get());



                    }).build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
