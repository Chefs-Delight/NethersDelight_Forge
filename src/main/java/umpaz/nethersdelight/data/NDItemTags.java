package umpaz.nethersdelight.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import umpaz.nethersdelight.common.registry.NDItems;
import umpaz.nethersdelight.common.tag.NDTags;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.Nullable;
import java.nio.file.Path;

public class NDItemTags extends ItemTagsProvider
{
    public NDItemTags(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, blockTagProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.registerModTags();
        this.registerMinecraftTags();
    }

    private void registerModTags() {
        tag(NDTags.RAW_STRIDER).add(NDItems.STRIDER_SLICE.get(), NDItems.GROUND_STRIDER.get());

        tag(NDTags.MEAL_ITEM).add(Items.MUSHROOM_STEW).add(Items.BEETROOT_SOUP).add(Items.RABBIT_STEW).add(Items.SUSPICIOUS_STEW)
                .add(ModItems.TOMATO_SAUCE.get()).add(ModItems.FRUIT_SALAD.get())
                .add(ModItems.MIXED_SALAD.get()).add(ModItems.NETHER_SALAD.get()).add(ModItems.COOKED_RICE.get())
                .add(ModItems.BEEF_STEW.get()).add(ModItems.CHICKEN_SOUP.get())
                .add(ModItems.VEGETABLE_SOUP.get()).add(ModItems.FISH_STEW.get())
                .add(ModItems.FRIED_RICE.get()).add(ModItems.PUMPKIN_SOUP.get()).add(ModItems.BAKED_COD_STEW.get())
                .add(ModItems.NOODLE_SOUP.get()).add(ModItems.BACON_AND_EGGS.get())
                .add(ModItems.PASTA_WITH_MEATBALLS.get()).add(ModItems.PASTA_WITH_MUTTON_CHOP.get()).add(ModItems.ROASTED_MUTTON_CHOPS.get())
                .add(ModItems.VEGETABLE_NOODLES.get()).add(ModItems.STEAK_AND_POTATOES.get()).add(ModItems.RATATOUILLE.get()).add(ModItems.SQUID_INK_PASTA.get())
                .add(ModItems.GRILLED_SALMON.get()).add(ModItems.ROAST_CHICKEN.get()).add(ModItems.STUFFED_PUMPKIN.get())
                .add(ModItems.HONEY_GLAZED_HAM.get()).add(ModItems.SHEPHERDS_PIE.get())
                .add(NDItems.WARPED_MOLDY_MEAT.get()).add(NDItems.GRILLED_STRIDER.get()).add(NDItems.STRIDER_MOSS_STEW.get())
                .add(NDItems.PLATE_OF_STUFFED_HOGLIN_SNOUT.get()).add(NDItems.PLATE_OF_STUFFED_HOGLIN_HAM.get()).add(NDItems.PLATE_OF_STUFFED_HOGLIN_ROAST.get());


        tag(NDTags.MACHETES).add(NDItems.IRON_MACHETE.get()).add(NDItems.DIAMOND_MACHETE.get())
                .add(NDItems.NETHERITE_MACHETE.get()).add(NDItems.GOLDEN_MACHETE.get())
                /*.add(ModItems.FLINT_KNIFE.get()).add(ModItems.IRON_KNIFE.get()).add(ModItems.GOLDEN_KNIFE.get())
                .add(ModItems.DIAMOND_KNIFE.get()).add(ModItems.NETHERITE_KNIFE.get())*/;


        tag(ModTags.CABBAGE_ROLL_INGREDIENTS).add(NDItems.HOGLIN_LOIN.get());

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