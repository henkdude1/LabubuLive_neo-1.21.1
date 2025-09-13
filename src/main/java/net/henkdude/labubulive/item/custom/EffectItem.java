package net.henkdude.labubulive.item.custom;

import net.henkdude.labubulive.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Item that gives a potion effect while in inventory.
 * Right–click to toggle the effect on/off per stack.
 */
public class EffectItem extends Item {
    private final Holder<MobEffect> effectHolder;
    private final int amplifier;

    public EffectItem(Properties properties, Holder<MobEffect> effectHolder, int amplifier) {
        super(properties);
        this.effectHolder = effectHolder;
        this.amplifier = amplifier;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide() && entity instanceof Player player) {
            if (isEnabled(stack)) {
                // give the effect every tick if enabled
                if (!player.hasEffect(effectHolder)) {
                    player.addEffect(new MobEffectInstance(effectHolder, 40, amplifier, true, false));
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            boolean enabled = !isEnabled(stack);
            setEnabled(stack, enabled);
            player.displayClientMessage(Component.literal("Effect " + (enabled ? "enabled" : "disabled")), true);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private boolean isEnabled(ItemStack stack) {
        // getOrDefault returns false if the component hasn’t been set yet
        return stack.getOrDefault(ModDataComponents.EFFECT_ENABLED.get(), Boolean.FALSE);
    }

    private void setEnabled(ItemStack stack, boolean enabled) {
        stack.set(ModDataComponents.EFFECT_ENABLED.get(), enabled);
    }
}
