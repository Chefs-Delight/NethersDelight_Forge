package umpaz.nethersdelight.data.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import umpaz.nethersdelight.common.registry.NDItems;
import umpaz.nethersdelight.common.tag.NDTags;
import umpaz.nethersdelight.data.builder.NDCookingPotRecipeBuilder;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class NDCookingPotRecipes extends RecipeProvider {

    public static final int FAST_COOKING = 100;      // 5 seconds
    public static final int NORMAL_COOKING = 200;    // 10 seconds
    public static final int SLOW_COOKING = 400;      // 20 seconds

    public static final float SMALL_EXP = 0.35F;
    public static final float MEDIUM_EXP = 1.0F;
    public static final float LARGE_EXP = 2.0F;

    public NDCookingPotRecipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        CookingPotRecipeBuilder.cookingPotRecipe(NDItems.STUFFED_HOGLIN.get(), 1, SLOW_COOKING, LARGE_EXP)
                .addIngredient(ModItems.NETHER_SALAD.get())
                .addIngredient(NDItems.RAW_STUFFED_HOGLIN.get())
                .addIngredient(ModItems.NETHER_SALAD.get())
                .unlockedByItems("has_raw_stuffed_hoglin", NDItems.RAW_STUFFED_HOGLIN.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer);
        NDCookingPotRecipeBuilder.cookingPotRecipe(NDItems.GRILLED_STRIDER.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(NDTags.RAW_STRIDER)
                .addIngredient(Items.WARPED_FUNGUS)
                .addIngredient(Items.CRIMSON_FUNGUS)
                .addIngredient(Items.WARPED_ROOTS)
                .addIngredient(Items.CRIMSON_ROOTS)
                .unlockedByItems("has_strider_slice", NDItems.STRIDER_SLICE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer);
        NDCookingPotRecipeBuilder.cookingPotRecipe(NDItems.MAGMA_GELATIN.get(), 1, NORMAL_COOKING, MEDIUM_EXP, Items.LAVA_BUCKET)
                .addIngredient(Items.MAGMA_CREAM)
                .addIngredient(Items.MAGMA_CREAM)
                .addIngredient(Items.MAGMA_CREAM)
                .addIngredient(NDItems.PROPELPEARL.get())
                .unlockedByItems("has_propelpearl", NDItems.PROPELPEARL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(consumer);
        NDCookingPotRecipeBuilder.cookingPotRecipe(NDItems.STRIDER_MOSS_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(Items.WARPED_FUNGUS)
                .addIngredient(Items.CRIMSON_FUNGUS)
                .addIngredient(Items.CRIMSON_ROOTS)
                .addIngredient(Items.WARPED_FUNGUS)
                .addIngredient(NDTags.RAW_STRIDER)
                .unlockedByItems("has_strider_slice", NDItems.STRIDER_SLICE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer);

    }
}
