package umpaz.nethersdelight.data.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import umpaz.nethersdelight.common.registry.NDItems;
import umpaz.nethersdelight.data.builder.NDCuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.common.tag.ForgeTags;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class NDCuttingBoardRecipes extends RecipeProvider {
    public NDCuttingBoardRecipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.STRIDER_SLICE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), NDItems.GROUND_STRIDER.get(), 2)
                .build(consumer);
        NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.CRIMSON_FUNGUS_COLONY.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.CRIMSON_FUNGUS, 5)
                .build(consumer);
        NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.WARPED_FUNGUS_COLONY.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.WARPED_FUNGUS, 5)
                .build(consumer);
        NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.HOGLIN_HIDE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.LEATHER, 4)
                .build(consumer);
        NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.PROPELPLANT_CANE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.GUNPOWDER)
                .build(consumer);
    }
}
