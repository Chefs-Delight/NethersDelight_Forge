package umpaz.nethersdelight.common;

import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDItems;


@Mod.EventBusSubscriber(modid = NethersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NDCommonSetup {
    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(NDCommonSetup::registerCompostables);
    }

    public static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(NDItems.WARPED_FUNGUS_COLONY.get(), 1.0F);
        ComposterBlock.COMPOSTABLES.put(NDItems.CRIMSON_FUNGUS_COLONY.get(), 1.0F);
        ComposterBlock.COMPOSTABLES.put(NDItems.PROPELPLANT_CANE.get(), 0.4F);
    }
}
