package umpaz.nethersdelight.data.worldgen.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDFeatures;

public class NDConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> PROPELPLANT_PATCH = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NethersDelight.MODID, "propelplant_patch"));

    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context) {
        context.register(
                PROPELPLANT_PATCH,
                new ConfiguredFeature<>(
                        NDFeatures.PROPELPLANT.get(),
                        new NoneFeatureConfiguration()
                )
        );
    }
}
