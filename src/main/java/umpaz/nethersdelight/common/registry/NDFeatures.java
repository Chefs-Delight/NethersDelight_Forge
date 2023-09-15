package umpaz.nethersdelight.common.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.world.level.levelgen.feature.PropelplantFeature;

public class NDFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.Keys.FEATURES, NethersDelight.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> PROPELPLANT = FEATURES.register("propelplant", () -> new PropelplantFeature(NoneFeatureConfiguration.CODEC));
}