package umpaz.nethersdelight.client.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.client.renderer.StoveRenderer;
import umpaz.nethersdelight.common.block.entity.BlackstoneStoveBlockEntity;
import umpaz.nethersdelight.common.registry.NDBlockEntityTypes;

@Mod.EventBusSubscriber(modid = NethersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NDClientSetupEvents {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(NDBlockEntityTypes.BLACKSTONE_STOVE.get(), StoveRenderer<BlackstoneStoveBlockEntity>::new);
    }
}
