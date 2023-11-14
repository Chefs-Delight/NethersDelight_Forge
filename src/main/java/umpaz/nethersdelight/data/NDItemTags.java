package umpaz.nethersdelight.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDItems;
import umpaz.nethersdelight.common.tag.NDTags;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class NDItemTags extends ItemTagsProvider {
    public NDItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagsProvider.TagLookup<Block>> blockTagsProvider, @Nullable ExistingFileHelper fileHelper) {
        super(
                packOutput,
                registries,
                blockTagsProvider,
                NethersDelight.MODID,
                fileHelper
        );
    }

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider registries) {
        this.registerModTags();
        this.registerMinecraftTags();
    }

    private void registerModTags() {
        tag(NDTags.RAW_STRIDER).add(
                NDItems.STRIDER_SLICE.get(), NDItems.GROUND_STRIDER.get()
        );

        tag(NDTags.MEAL_ITEM).add(
                Items.MUSHROOM_STEW, Items.BEETROOT_SOUP, Items.RABBIT_STEW, Items.SUSPICIOUS_STEW,
                ModItems.TOMATO_SAUCE.get(), ModItems.FRUIT_SALAD.get(), ModItems.MIXED_SALAD.get(),
                ModItems.NETHER_SALAD.get(), ModItems.COOKED_RICE.get(), ModItems.BEEF_STEW.get(),
                ModItems.CHICKEN_SOUP.get(), ModItems.VEGETABLE_SOUP.get(),
                ModItems.FISH_STEW.get(), ModItems.FRIED_RICE.get(), ModItems.PUMPKIN_SOUP.get(),
                ModItems.BAKED_COD_STEW.get(), ModItems.NOODLE_SOUP.get(), ModItems.BACON_AND_EGGS.get(),
                ModItems.PASTA_WITH_MEATBALLS.get(), ModItems.PASTA_WITH_MUTTON_CHOP.get(),
                ModItems.ROASTED_MUTTON_CHOPS.get(), ModItems.VEGETABLE_NOODLES.get(),
                ModItems.STEAK_AND_POTATOES.get(), ModItems.RATATOUILLE.get(), ModItems.SQUID_INK_PASTA.get(),
                ModItems.GRILLED_SALMON.get(), ModItems.ROAST_CHICKEN.get(), ModItems.STUFFED_PUMPKIN.get(),
                ModItems.STUFFED_POTATO.get(), ModItems.HONEY_GLAZED_HAM.get(), ModItems.SHEPHERDS_PIE.get(),
                NDItems.WARPED_MOLDY_MEAT.get(), NDItems.GRILLED_STRIDER.get(), NDItems.STRIDER_MOSS_STEW.get(),
                NDItems.PLATE_OF_STUFFED_HOGLIN_SNOUT.get(), NDItems.PLATE_OF_STUFFED_HOGLIN_HAM.get(),
                NDItems.PLATE_OF_STUFFED_HOGLIN_ROAST.get()
        );

        tag(NDTags.MACHETES).add(
                NDItems.IRON_MACHETE.get(),
                NDItems.GOLDEN_MACHETE.get(),
                NDItems.DIAMOND_MACHETE.get(),
                NDItems.NETHERITE_MACHETE.get()
        );

        tag(ModTags.CABBAGE_ROLL_INGREDIENTS).add(
                NDItems.HOGLIN_LOIN.get()
        );

    }

    private void registerMinecraftTags() {
        tag(ItemTags.SOUL_FIRE_BASE_BLOCKS).add(
                NDItems.SOUL_COMPOST.get(),
                NDItems.RICH_SOUL_SOIL.get()
        );

        tag(ItemTags.PIGLIN_FOOD).add(
                NDItems.HOGLIN_LOIN.get(),
                NDItems.HOGLIN_SIRLOIN.get()
        );
        tag(ItemTags.PIGLIN_LOVED).add(
                NDItems.HOGLIN_HIDE.get(),
                NDItems.RAW_STUFFED_HOGLIN.get(),
                NDItems.STUFFED_HOGLIN.get(),
                NDItems.GOLDEN_MACHETE.get()
        );
        tag(ItemTags.SMALL_FLOWERS).add(
                NDItems.MIMICARNATION.get()
        );
    }
}