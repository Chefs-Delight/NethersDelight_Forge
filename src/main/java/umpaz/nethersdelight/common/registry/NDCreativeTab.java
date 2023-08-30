package umpaz.nethersdelight.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.utility.NDTextUtils;


@Mod.EventBusSubscriber(modid = NethersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NDCreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NethersDelight.MODID);

    public static final RegistryObject<CreativeModeTab> NETHERS_DELIGHT_TAB = TABS.register("main",
            () -> CreativeModeTab.builder()
                    .title(NDTextUtils.getTranslation("itemGroup.main"))
                    .icon(NDItems.BLACKSTONE_STOVE.get()::getDefaultInstance)
                    .build()
    );

    private static void acceptFoodsAndDrinksTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() != CreativeModeTabs.FOOD_AND_DRINKS) return;

        acceptFoodItems(event);
    }

    private static void acceptToolsAndUtilitiesContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() != CreativeModeTabs.TOOLS_AND_UTILITIES) return;

        acceptMacheteItems(event);
    }

    private static void acceptCombatContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() != CreativeModeTabs.COMBAT) return;

        acceptMacheteItems(event);
    }

    private static void acceptMainTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() != NETHERS_DELIGHT_TAB.get()) return;

        acceptWorkstations(event);
        acceptBlocks(event);
        acceptItems(event);
    }

    private static void acceptWorkstations(BuildCreativeModeTabContentsEvent event) {
        event.accept(NDItems.BLACKSTONE_STOVE.get());
        event.accept(NDItems.BLACKSTONE_FURNACE.get());
        event.accept(NDItems.NETHER_BRICK_SMOKER.get());
        event.accept(NDItems.BLACKSTONE_BLAST_FURNACE.get());
    }

    private static void acceptBlocks(BuildCreativeModeTabContentsEvent event) {
        event.accept(NDItems.CRIMSON_FUNGUS_COLONY.get());
        event.accept(NDItems.WARPED_FUNGUS_COLONY.get());
        event.accept(NDItems.SOUL_COMPOST.get());
        event.accept(NDItems.RICH_SOUL_SOIL.get());
        event.accept(NDItems.MIMICARNATION.get());
        event.accept(NDItems.HOGLIN_TROPHY.get());
        event.accept(NDItems.STUFFED_HOGLIN.get());
        event.accept(NDItems.PROPELPLANT_CANE.get());
        event.accept(NDItems.PROPELPLANT_TORCH.get());
    }

    private static void acceptItems(BuildCreativeModeTabContentsEvent event) {
        acceptMacheteItems(event);
        acceptFoodItems(event);

        event.accept(NDItems.HOGLIN_HIDE.get());
        event.getEntries().putBefore(
                NDItems.STUFFED_HOGLIN.get().getDefaultInstance(),
                NDItems.RAW_STUFFED_HOGLIN.get().getDefaultInstance(),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    private static void acceptMacheteItems(BuildCreativeModeTabContentsEvent event) {
        event.accept(NDItems.IRON_MACHETE.get());
        event.accept(NDItems.GOLDEN_MACHETE.get());
        event.accept(NDItems.DIAMOND_MACHETE.get());
        event.accept(NDItems.NETHERITE_MACHETE.get());
    }

    private static void acceptFoodItems(BuildCreativeModeTabContentsEvent event) {
        event.accept(NDItems.HOGLIN_LOIN.get());
        event.accept(NDItems.HOGLIN_SIRLOIN.get());
        event.accept(NDItems.HOGLIN_EAR.get());
        event.accept(NDItems.STRIDER_SLICE.get());
        event.accept(NDItems.GRILLED_STRIDER.get());
        event.accept(NDItems.GROUND_STRIDER.get());
        event.accept(NDItems.WARPED_MOLDY_MEAT.get());
        event.accept(NDItems.STRIDER_MOSS_STEW.get());
        event.accept(NDItems.PLATE_OF_STUFFED_HOGLIN_SNOUT.get());
        event.accept(NDItems.PLATE_OF_STUFFED_HOGLIN_HAM.get());
        event.accept(NDItems.PLATE_OF_STUFFED_HOGLIN_ROAST.get());
        event.accept(NDItems.NETHER_SKEWER.get());
        event.accept(NDItems.MAGMA_GELATIN.get());
        event.getEntries().putAfter(
                NDItems.PROPELPLANT_CANE.get().getDefaultInstance(),
                NDItems.PROPELPEARL.get().getDefaultInstance(),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        acceptFoodsAndDrinksTabContents(event);
        acceptToolsAndUtilitiesContents(event);
        acceptMainTabContents(event);
    }
}
