package umpaz.nethersdelight.common.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.world.feature.PropelplantFeature;

public class NDBiomeFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, NethersDelight.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> PROPELPLANT = FEATURES.register("propelplant", () -> new PropelplantFeature(NoneFeatureConfiguration.CODEC));

}