package umpaz.nethersdelight;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import umpaz.nethersdelight.common.registry.*;

@Mod(NethersDelight.MODID)
public class NethersDelight
{
	public static final String MODID = "nethersdelight";
	public static final Logger LOGGER = LogUtils.getLogger();

	public NethersDelight() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		NDItems.ITEMS.register(modEventBus);
		NDBlocks.BLOCKS.register(modEventBus);
		NDLootModifiers.LOOT_MODIFIERS.register(modEventBus);
		NDFeatures.FEATURES.register(modEventBus);
		NDBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
		NDCreativeTab.TABS.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);
	}
}
