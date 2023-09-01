package umpaz.nethersdelight;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import umpaz.nethersdelight.common.registry.*;

@Mod(NethersDelight.MODID)
public class NethersDelight
{
	public static final String MODID = "nethersdelight";

	public NethersDelight() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		NDItems.ITEMS.register(modEventBus);
		NDBlocks.BLOCKS.register(modEventBus);
		NDFeatures.FEATURES.register(modEventBus);
		NDBlockEntityTypes.TILES.register(modEventBus);
		NDCreativeTab.TABS.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);
	}
}
