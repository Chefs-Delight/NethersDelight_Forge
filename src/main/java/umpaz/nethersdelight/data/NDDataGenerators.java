package umpaz.nethersdelight.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import umpaz.nethersdelight.NethersDelight;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = NethersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NDDataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        DataProvider.Factory<NDBlockTags> blockTagsFactory = (PackOutput output) -> {
            return new NDBlockTags(output, registries, fileHelper);
        };
        NDBlockTags blockTagsProvider = generator.addProvider(event.includeServer(), blockTagsFactory);

        DataProvider.Factory<NDItemTags> itemTagsFactory = (PackOutput output) -> {
            return new NDItemTags(output, registries, blockTagsProvider.contentsGetter(), fileHelper);
        };
        NDItemTags itemTagsProvider = generator.addProvider(event.includeServer(), itemTagsFactory);

        DataProvider.Factory<NDRecipes> recipesFactory = NDRecipes::new;
        generator.addProvider(event.includeServer(), recipesFactory);
        DataProvider.Factory<NDLang> langFactory = NDLang::new;
        generator.addProvider(event.includeServer(), langFactory);
        //generator.addProvider(event.includeServer(), new Advancements(generator));
    }
}