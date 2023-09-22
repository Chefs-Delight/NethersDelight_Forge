package umpaz.nethersdelight.data.worldgen.placement;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.data.worldgen.features.NDConfiguredFeatures;

import java.util.List;

public class NDPlacements {
    public static final ResourceKey<PlacedFeature> PROPELPLANT_PATCH = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(NethersDelight.MODID, "propelplant_patch"));

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> propelplantPatchConfiguredFeature = configuredFeatureLookup.getOrThrow(NDConfiguredFeatures.PROPELPLANT_PATCH);

        context.register(
                PROPELPLANT_PATCH,
                new PlacedFeature(
                        propelplantPatchConfiguredFeature,
                        List.of(
                                CountPlacement.of(8),
                                InSquarePlacement.spread(),
                                PlacementUtils.FULL_RANGE,
                                BiomeFilter.biome()
                        )
                )
        );
    }
}
