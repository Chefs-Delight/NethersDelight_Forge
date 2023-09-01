package umpaz.nethersdelight.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.data.worldgen.biome.NDBiomeModifiers;
import umpaz.nethersdelight.data.worldgen.features.NDConfiguredFeatures;
import umpaz.nethersdelight.data.worldgen.placement.NDPlacements;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = NethersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NDDataGenerators
{
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, NDConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, NDPlacements::bootstrap)
                .add(ForgeRegistries.Keys.BIOME_MODIFIERS, NDBiomeModifiers::bootstrap);

        generator.addProvider(true, new DatapackBuiltinEntriesProvider(
                output,
                registries,
                registrySetBuilder,
                Set.of(NethersDelight.MODID)
        ));

        NDBlockTags blockTagsProvider = generator.addProvider(event.includeServer(), new NDBlockTags(output, registries, fileHelper));
        NDItemTags itemTagsProvider = generator.addProvider(event.includeServer(), new NDItemTags(output, registries, blockTagsProvider.contentsGetter(), fileHelper));
        generator.addProvider(event.includeServer(), new NDRecipes(output));
        //generator.addProvider(event.includeServer(), new Advancements(generator));

        generator.addProvider(event.includeClient(), new NDLang(output));
    }
}