package net.henkdude.labubulive.block;

import net.henkdude.labubulive.LabubuLive;
import net.henkdude.labubulive.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    // NeoForge helper for the vanilla BLOCK registry
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LabubuLive.MOD_ID);

    // Your block
    public static final DeferredBlock<Block> LABUBU_BLINDBOX = BLOCKS.register(
            "labubu_blindbox",
            () -> new Block(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.AMETHYST))
    );

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);

        // Register the BlockItem with the same id
        ModItems.ITEMS.register("labubu_blindbox",
                () -> new BlockItem(LABUBU_BLINDBOX.get(), new Item.Properties()));
    }
}
