package net.henkdude.labubulive.item.custom;

import net.henkdude.labubulive.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Item that toggles creative-style flight when enabled.
 */
public class FlightItem extends Item {
    public FlightItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide() && entity instanceof Player player) {
            boolean enabled = isEnabled(stack);

            if (enabled && !player.getAbilities().mayfly) {
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
            }

            if (!enabled && player.getAbilities().mayfly && !player.isCreative() && !player.isSpectator()) {
                player.getAbilities().flying = false;
                player.getAbilities().mayfly = false;
                player.onUpdateAbilities();
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            boolean enabled = !isEnabled(stack);
            setEnabled(stack, enabled);
            player.displayClientMessage(Component.literal("Flight " + (enabled ? "enabled" : "disabled")), true);

            if (enabled) {
                player.getAbilities().mayfly = true;
            } else if (!player.isCreative() && !player.isSpectator()) {
                player.getAbilities().flying = false;
                player.getAbilities().mayfly = false;
            }
            player.onUpdateAbilities();
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private boolean isEnabled(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.EFFECT_ENABLED.get(), Boolean.FALSE);
    }

    private void setEnabled(ItemStack stack, boolean enabled) {
        stack.set(ModDataComponents.EFFECT_ENABLED.get(), enabled);
    }
}
