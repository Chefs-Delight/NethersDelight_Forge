package umpaz.nethersdelight.common;

import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import umpaz.nethersdelight.common.registry.NDBlocks;
import umpaz.nethersdelight.common.registry.NDItems;
import umpaz.nethersdelight.common.world.NDGeneration;
import vectorwing.farmersdelight.common.registry.ModItems;

public class NDCommonSetup {

    public static void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            registerCompostables();
            NDGeneration.registerNDGeneration();
        });
    }

    public static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(NDItems.WARPED_FUNGUS_COLONY.get(), 1.0F);
        ComposterBlock.COMPOSTABLES.put(NDItems.CRIMSON_FUNGUS_COLONY.get(), 1.0F);
        ComposterBlock.COMPOSTABLES.put(NDItems.PROPELPLANT_CANE.get(), 0.4F);
    }
}
