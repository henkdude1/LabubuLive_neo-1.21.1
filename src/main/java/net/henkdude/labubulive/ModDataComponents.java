package net.henkdude.labubulive;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, LabubuLive.MOD_ID);

    public static final RegistryObject<DataComponentType<Boolean>> EFFECT_ENABLED =
            DATA_COMPONENTS.register("effect_enabled",
                    () -> new DataComponentType.Builder<Boolean>()
                            .persistent(Codec.BOOL) // <— use Mojang’s codec
                            .build());

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }
}
