package io.github.amdyep.doesittick;

import io.github.amdyep.doesittick.api.Extended$EntityType;
import io.github.amdyep.doesittick.cfg.DoesItTickConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DoesItTick.ID)
public class DoesItTick {
    public static final String ID = "doesittick";

    public DoesItTick() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DoesItTickConfig.SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> BuiltInRegistries.ENTITY_TYPE.forEach(type -> {
            ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(type);
            if (DoesItTickConfig.ENTITIES_WHITELIST.get().contains(id.toString()) ||
                    DoesItTickConfig.ENTITIES_MOD_ID_LIST.get().contains(id.getNamespace())) {
                ((Extended$EntityType) type).doesittick$markAlwaysTick(true);
            }
        }));
    }
}
