package umpaz.nethersdelight.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import umpaz.nethersdelight.NethersDelight;

@Mod.EventBusSubscriber(modid = NethersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NDDataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        NDBlockTags blockTags = new NDBlockTags(generator, helper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new NDItemTags(generator, blockTags, NethersDelight.MODID, helper));
        generator.addProvider(event.includeServer(), new NDRecipes(generator));
        //generator.addProvider(event.includeServer(), new Advancements(generator));

        generator.addProvider(event.includeClient(), new NDLang(generator));
    }
}