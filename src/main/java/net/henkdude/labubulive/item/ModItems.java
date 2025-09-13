package net.henkdude.labubulive.item;

import net.henkdude.labubulive.LabubuLive;
import net.henkdude.labubulive.item.custom.EffectItem;
import net.henkdude.labubulive.item.custom.FlightItem;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    // NeoForge item register
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LabubuLive.MOD_ID);

    public static final DeferredItem<Item> LABUBU_LUCK =
            ITEMS.register("labubu_luck",
                    () -> new EffectItem(new Item.Properties().stacksTo(1), MobEffects.LUCK, 0));

    public static final DeferredItem<Item> LABUBU_CHECKMATE =
            ITEMS.register("labubu_checkmate",
                    () -> new EffectItem(new Item.Properties().stacksTo(1), MobEffects.DAMAGE_BOOST, 1));

    public static final DeferredItem<Item> LABUBU_FLIP_WITH_ME =
            ITEMS.register("labubu_flip_with_me",
                    () -> new EffectItem(new Item.Properties().stacksTo(1), MobEffects.JUMP, 1));

    public static final DeferredItem<Item> LABUBU_PUMPKIN =
            ITEMS.register("labubu_pumpkin",
                    () -> new EffectItem(new Item.Properties().stacksTo(1), MobEffects.DAMAGE_RESISTANCE, 1));

    public static final DeferredItem<Item> LABUBU_FALL =
            ITEMS.register("labubu_fall",
                    () -> new EffectItem(new Item.Properties().stacksTo(1), MobEffects.SLOW_FALLING, 0));

    public static final DeferredItem<Item> LABUBU_SKATER =
            ITEMS.register("labubu_skater",
                    () -> new EffectItem(new Item.Properties().stacksTo(1), MobEffects.MOVEMENT_SPEED, 1));

    public static final DeferredItem<Item> LABUBU_MINER =
            ITEMS.register("labubu_miner",
                    () -> new EffectItem(new Item.Properties().stacksTo(1), MobEffects.DIG_SPEED, 1));

    public static final DeferredItem<Item> LABUBU_ANGEL =
            ITEMS.register("labubu_angel",
                    () -> new FlightItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
