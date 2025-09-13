package net.henkdude.labubulive;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    // Create the DeferredRegister for DataComponentTypes
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, LabubuLive.MOD_ID);

    // Register your custom component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> EFFECT_ENABLED =
            DATA_COMPONENTS.register("effect_enabled",
                    () -> new DataComponentType.Builder<Boolean>()
                            .persistent(Codec.BOOL)
                            .build());

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }
}
