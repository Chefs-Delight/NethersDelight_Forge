package umpaz.nethersdelight.data.worldgen.biome;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.data.worldgen.placement.NDPlacements;

public class NDBiomeModifiers {
    private static final ResourceKey<BiomeModifier> ADD_PROPELPLANT_PATCH_FEATURE = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(NethersDelight.MODID, "propelplant_patch"));

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatureLookup = context.lookup(Registries.PLACED_FEATURE);
        Holder<PlacedFeature> propelplantPatchPlacedFeature = placedFeatureLookup.getOrThrow(NDPlacements.PROPELPLANT_PATCH);

        HolderGetter<Biome> biomeLookup = context.lookup(Registries.BIOME);
        Holder<Biome> crimsonForest = biomeLookup.getOrThrow(Biomes.CRIMSON_FOREST);

        context.register(
                ADD_PROPELPLANT_PATCH_FEATURE,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(crimsonForest),
                        HolderSet.direct(propelplantPatchPlacedFeature),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

    }
}
