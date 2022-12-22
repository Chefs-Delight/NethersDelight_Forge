package umpaz.nethersdelight.common.world;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDBiomeFeatures;
import umpaz.nethersdelight.common.registry.NDBlocks;

import java.util.List;

public class NDGeneration {

    public static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> FEATURE_PATCH_PROPELPLANT;

    public static Holder<PlacedFeature> PATCH_PROPELPLANT;

    static Holder<PlacedFeature> registerPlacement(ResourceLocation id, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, id, new PlacedFeature(Holder.hackyErase(feature), List.of(modifiers)));
    }

    protected static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(ResourceLocation id, F feature, FC featureConfig) {
        return register(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, featureConfig));
    }

    private static <V extends T, T> Holder<V> register(Registry<T> registry, ResourceLocation id, V value) {
        return (Holder<V>) BuiltinRegistries.<T>register(registry, id, value);
    }

    public static void registerNDGeneration() {

        FEATURE_PATCH_PROPELPLANT = register(new ResourceLocation(NethersDelight.MODID, "patch_propelplant"),
                NDBiomeFeatures.PROPELPLANT.get(), FeatureConfiguration.NONE);

        PATCH_PROPELPLANT = registerPlacement(new ResourceLocation(NethersDelight.MODID, "patch_propelplant"),
                FEATURE_PATCH_PROPELPLANT, CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
}